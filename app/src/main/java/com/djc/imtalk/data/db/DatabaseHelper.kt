package com.djc.imtalk.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.djc.imtalk.app.IMApplication
import org.jetbrains.anko.db.*

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 15
 * 邮箱    ：894230813@qq.com
 */
class DatabaseHelper(
    ctx: Context = IMApplication.instance
) :
    ManagedSQLiteOpenHelper(ctx, NAME, null, VERSION) {

    companion object {
        const val NAME = "im.db"
        const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            ContactTable.NAME, true, ContactTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ContactTable.CONTACT to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(ContactTable.NAME, true)
        onCreate(db)
    }
}