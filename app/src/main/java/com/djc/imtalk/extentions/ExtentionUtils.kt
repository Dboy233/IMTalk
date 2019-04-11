package com.djc.imtalk.extentions

/**
 *@author ： created by dujiangchuan
 * 时间    ：2019/4/9 14
 * 邮箱    ：894230813@qq.com
 */
// 3-10位的字母下划线和数字组成。不能以数字或下划线开头。只能已字母开头。允许全部是字母
fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-z][a-zA-Z0-9_]{3,20}$"))

//不能全是数字 不能全是字母 不能全是符号 长度不能少于6位
fun String.isValidPassword(): Boolean = this.matches(Regex("(?!^\\\\d+\$)(?!^[a-zA-Z]+\$)(?!^[_#@]+\$).{6,}"))

//将MutableMap转换成Pair类型的数组
fun <K, V> MutableMap<K, V>.toVarargArray(): Array<Pair<K, V>> =
    map {
        Pair(it.key, it.value)
    }.toTypedArray()
