package com.cqteam.lib.utils.sp

import android.content.Context

/**
 *
 * @Description:    SharedPreferences的管理类，get,put为默认的，
 * 如果有定制的，比如用户信息等等，调用[getSpHelper],用户清楚时可以直接清楚
 * @Author:         koloces
 * @CreateDate:     2020/10/22 10:55
 */
object SP {
    val sps: MutableMap<String, SPHelper> by lazy {
        mutableMapOf<String, SPHelper>()
    }
    private var defaultSp: SPHelper? = null
    private val DEFAULT = "default"
    private var mContext: Context? = null

    fun init(context: Context) {
        mContext = context
        defaultSp = SPHelper(context, DEFAULT)
    }

    fun put(key: String?, obj: Any?) {
        defaultSp?.put(key, obj)
    }

    fun putStringList(key: String, list: List<String>) {
        defaultSp?.putStringList(key, list)
    }

    fun clean() {
        defaultSp?.clean()
    }

    fun remove(key: String) {
        defaultSp?.remove(key)
    }

    fun getString(key: String, default: String = ""): String {
        return defaultSp?.getString(key, default) ?: ""
    }

    fun getInt(key: String, default: Int): Int {
        return defaultSp?.getInt(key, default) ?: default
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return defaultSp?.getBoolean(key, default) ?: default
    }

    fun getFloat(key: String, default: Float): Float {
        return defaultSp?.getFloat(key, default) ?: default
    }

    fun getLong(key: String, default: Long): Long {
        return defaultSp?.getLong(key, default) ?: default
    }

    fun getStringList(key: String): List<String> {
        return defaultSp?.getStringList(key) ?: mutableListOf()
    }

    /**
     * 获取特定的SPHelper
     */
    fun getSpHelper(tag: String): SPHelper {
        if (mContext == null) {
            throw SPNotInitException()
        } else {
            var spHelper = sps[tag]
            if (spHelper == null) {
                spHelper = SPHelper(mContext!!, tag)
            }
            sps[tag] = spHelper
            return spHelper
        }
    }

    fun getDefaultSPHelper(): SPHelper {
        if (mContext == null) {
            throw SPNotInitException()
        }
        return defaultSp!!
    }

    private class SPNotInitException : Exception("UtilsManager Not init")
}