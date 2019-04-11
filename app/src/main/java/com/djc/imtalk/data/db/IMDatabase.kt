package com.djc.imtalk.data.db

import com.djc.imtalk.extentions.toVarargArray
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 15
 * 邮箱    ：894230813@qq.com
 */
class IMDatabase {
    companion object {
        val databaseHelper = DatabaseHelper()
        val instance = IMDatabase()
    }

    //保存联系人
    fun saveContact(contact: Contact) {
        databaseHelper.use {
            //SQLiteDatabase的扩展方法
            insert(ContactTable.NAME, *contact.map.toVarargArray())
        }
    }

    //获取所有联系人
    fun getAllContacts(): List<Contact> =
        databaseHelper.use {
            select(ContactTable.NAME).parseList(object : MapRowParser<Contact> {
                override fun parseRow(columns: Map<String, Any?>): Contact =
                    Contact(columns.toMutableMap())

            })
        }


}