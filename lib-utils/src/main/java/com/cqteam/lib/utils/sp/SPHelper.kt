package com.cqteam.lib.utils.sp

import android.R.integer
import android.content.Context
import android.content.SharedPreferences

/**
 *
 * @Description:
 * @Author:         koloces
 * @CreateDate:     2020/10/22 10:34
 * spTag : SP的tag
 */
class SPHelper(context: Context, spTag: String) {
    val spf: SharedPreferences = context.getSharedPreferences(spTag, Context.MODE_PRIVATE)

    /**
     * 通过 instanceof 来判断数据类型再强转成对应的数据类型进行存储
     *
     * @param key
     * @param obj
     */
    fun put(key: String?, obj: Any?) {
        if (obj == null) {
            return
        }
        val editor = getEdit()
        val simpleName = obj.javaClass.simpleName
        if (obj is String || "String" == simpleName) {
            editor.putString(key, obj as String?)
        } else if (obj is Long || "Long" == simpleName) {
            editor.putLong(key, (obj as Long?)!!)
        } else if (obj is integer || "Integer" == simpleName) {
            editor.putInt(key, obj as Int)
        } else if (obj is Boolean || "Boolean" == simpleName) {
            editor.putBoolean(key, (obj as Boolean?)!!)
        } else if (obj is Float || "Float" == simpleName) {
            editor.putFloat(key, (obj as Float?)!!)
        }
        editor.apply()
    }

    fun putStringList(key: String, list: List<String>) {
        getEdit().putStringSet(key, list.toSet())
    }

    fun clean() {
        getEdit().clear().apply()
    }

    fun remove(key: String) {
        getEdit().remove(key)
    }

    fun getString(key: String, default: String = ""): String {
        return spf.getString(key, default) ?: ""
    }

    fun getInt(key: String, default: Int): Int {
        return spf.getInt(key, default)
    }

    fun getBoolean(key: String, default: Boolean): Boolean {
        return spf.getBoolean(key, default)
    }

    fun getFloat(key: String, default: Float): Float {
        return spf.getFloat(key, default)
    }

    fun getLong(key: String, default: Long): Long {
        return spf.getLong(key, default)
    }

    fun getStringList(key: String): List<String> {
        val stringSet = spf.getStringSet(key, mutableSetOf())
        val list = stringSet?.toList()
        return list ?: mutableListOf()
    }

    private fun getEdit(): SharedPreferences.Editor {
        return spf.edit()
    }
}