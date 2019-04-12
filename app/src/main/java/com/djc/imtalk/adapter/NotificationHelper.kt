package com.djc.imtalk.adapter

import android.app.*
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Build
import com.djc.imtalk.R
import com.djc.imtalk.ui.activity.ChatActivity

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/12 20
 * 邮箱    ：894230813@qq.com
 */

internal class NotificationHelper(ctx: Context) : ContextWrapper(ctx) {
    private val manager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    }

    init {
        val chan1: NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chan1 = NotificationChannel(
                PRIMARY_CHANNEL,

                "IMTalk", NotificationManager.IMPORTANCE_DEFAULT
            )

            chan1.lightColor = Color.GREEN

            chan1.lockscreenVisibility = Notification.VISIBILITY_PRIVATE

            manager.createNotificationChannel(chan1)
        }

        val chan2: NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chan2 = NotificationChannel(
                SECONDARY_CHANNEL,

                "IMTalk", NotificationManager.IMPORTANCE_HIGH
            )

            chan2.lightColor = Color.BLUE

            chan2.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            manager.createNotificationChannel(chan2)
        }


    }


    /**

     * Get a notification of type 1



     * Provide the builder rather than the notification it's self as useful for making notification

     * changes.



     * @param title the title of the notification

     * *

     * @param body the body text for the notification

     * *

     * @return the builder as it keeps a reference to the notification (since API 24)

     */

    fun getNotification1(title: String, body: String): Notification.Builder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(applicationContext, PRIMARY_CHANNEL)

                .setContentTitle(title)

                .setContentText(body)

                .setSmallIcon(R.drawable.ic_small_chat)

                .setAutoCancel(true)

        } else {
            Notification.Builder(applicationContext)
                .setContentTitle(title)

                .setContentText(body)

                .setSmallIcon(R.drawable.ic_small_chat)

                .setAutoCancel(true)
        }

    }


    /**

     * Build notification for secondary channel.



     * @param title Title for notification.

     * *

     * @param body Message for notification.

     * *

     * @return A Notification.Builder configured with the selected channel and details

     */

    fun getNotification2(title: String, body: String): Notification.Builder {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(applicationContext, SECONDARY_CHANNEL)

                .setContentTitle(title)

                .setContentText(body)

                .setSmallIcon(R.drawable.ic_small_chat)
                .setAutoCancel(true)
        } else {
            Notification.Builder(applicationContext)

                .setContentTitle(title)

                .setContentText(body)

                .setSmallIcon(R.drawable.ic_small_chat)

                .setAutoCancel(true)
        }

    }

    fun getPendingIntent(packageContext: Context, intentClass: Class<*>): PendingIntent? {
        val intent = Intent(packageContext, intentClass)
        val taskStackBuilder =
            TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
        return taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun getPendingIntent(intent: Intent): PendingIntent? {
        val taskStackBuilder =
            TaskStackBuilder.create(this).addParentStack(ChatActivity::class.java).addNextIntent(intent)
        return taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    /**

     * Send a notification.



     * @param id The ID of the notification

     * *

     * @param notification The notification object

     */

    fun notify(id: Int, notification: Notification.Builder) {

        manager.notify(id, notification.build())

    }


    /**

     * Get the small icon for this app



     * @return The small icon resource id

     */

    private val smallIcon: Int
        get() = android.R.drawable.stat_notify_chat


    companion object {

        val PRIMARY_CHANNEL = "default"

        val SECONDARY_CHANNEL = "second"

    }
}