package com.cqteam.lib.utils

import android.util.Log

/**
 *
 * @Description:    打印工具类
 * @Author:         koloces
 * @CreateDate:     2020/6/17 9:51
 */
object LogUtils {
    private const val LOG_LEVER_0 = 0
    private const val LOG_LEVER_V = 1
    private const val LOG_LEVER_D = 2
    private const val LOG_LEVER_I = 3
    private const val LOG_LEVER_W = 4
    private const val LOG_LEVER_E = 5

    private var LOG_LEVER = LOG_LEVER_E

    fun init(isDebug: Boolean) {
        LOG_LEVER = if (isDebug) LOG_LEVER_E else LOG_LEVER_0
    }

    fun e(str: String?) {
        e(getClassTag(), str)
    }


    fun e(tag: String, str: String?) {
        if (StringUtils.isEmpty(str)) return
        var TAG = tag
        if (!tag.contains(".kt") && !tag.contains(".java")){
            TAG = getClassTag(tag)
        }
        if (LOG_LEVER >= LOG_LEVER_E) Log.e(TAG, str)
    }

    fun v(tag: String, str: String?) {
        if (StringUtils.isEmpty(str)) return
        var TAG = tag
        if (!tag.contains(".kt") && !tag.contains(".java")){
            TAG = getClassTag(tag)
        }
        if (LOG_LEVER >= LOG_LEVER_V) Log.v(TAG, str)
    }

    fun v(str: String?) {
        v(getClassTag(), str)
    }

    fun i(tag: String, str: String?) {
        if (StringUtils.isEmpty(str)) return
        var TAG = tag
        if (!tag.contains(".kt") && !tag.contains(".java")){
            TAG = getClassTag(tag)
        }
        if (LOG_LEVER >= LOG_LEVER_D) Log.i(TAG, str)
    }
    fun i(str: String?){
        i(getClassTag(),str)
    }

    fun d(tag: String, str: String?) {
        if (StringUtils.isEmpty(str)) return
        var TAG = tag
        if (!tag.contains(".kt") && !tag.contains(".java")){
            TAG = getClassTag(tag)
        }
        if (LOG_LEVER >= LOG_LEVER_I) Log.d(TAG, str)
    }
    fun d(str: String?){
        d(getClassTag(),str)
    }

    fun w(tag: String, str: String?) {
        if (StringUtils.isEmpty(str)) return
        var TAG = tag
        if (!tag.contains(".kt") && !tag.contains(".java")){
            TAG = getClassTag(tag)
        }
        if (LOG_LEVER >= LOG_LEVER_W) Log.w(TAG, str)
    }
    fun w(str: String?){
        w(getClassTag(),str)
    }

    private fun getClassTag(tag: String): String {
        val s = Thread.currentThread().stackTrace
        val lines = s[4].lineNumber
        var className: String = s[4].fileName
        //生成指向java的字符串 加入到TAG标签里面
        return "$tag-->($className:$lines)-->"
    }

    private fun getClassTag() : String{
        val s = Thread.currentThread().stackTrace
        val lines = s[4].lineNumber
        var className: String = s[4].fileName
        //生成指向java的字符串 加入到TAG标签里面
        return "-->($className:$lines)-->"
    }
}