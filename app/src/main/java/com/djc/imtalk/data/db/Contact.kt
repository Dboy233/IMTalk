package com.djc.imtalk.data.db

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/11 15
 * 邮箱    ：894230813@qq.com
 */
data class Contact(val map: MutableMap<String, Any?>) {
    val _id by map
    val name by map

}

