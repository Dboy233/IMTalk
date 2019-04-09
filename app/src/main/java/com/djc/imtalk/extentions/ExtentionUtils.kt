package com.djc.imtalk.extentions

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 14
 * 邮箱    ：894230813@qq.com
 */
fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}&"))

fun String.isValidPassword(): Boolean = this.matches(Regex("^[0-9]{3,20}&"))