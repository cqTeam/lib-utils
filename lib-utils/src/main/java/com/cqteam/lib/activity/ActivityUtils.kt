package com.cqteam.lib.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import java.io.Serializable
import java.util.*
import kotlin.system.exitProcess

/**
 *
 * @Description:    Activity管理工具类
 * @Author:         koloces
 * @CreateDate:     2020/6/17 15:20
 */
class ActivityUtils private constructor(){
    companion object{
        private var instance : ActivityUtils? = null
        fun INSTANCE():ActivityUtils{
            if (instance == null){
                synchronized(ActivityUtils){
                    if (instance == null){
                        instance = ActivityUtils()
                    }
                }
            }
            return instance!!
        }
    }
    private val activityStack: Stack<Activity> by lazy { Stack<Activity>() }

    fun addActivity(activity: Activity?) {
        if (activity == null)return
        activityStack.add(activity)
    }

    /**
     * 获取栈顶的activity，先进后出原则
     * @return
     */
    fun getLastActivity(): Activity? {
        return activityStack.lastElement()
    }

    /**
     * 移除一个activity
     * @param activity
     */
    fun removeActivity(activity: Activity?) {
        if (activityStack.size > 0) {
            if (activity != null) {
                activityStack.remove(activity)
            }
        }
    }

    /**
     * 移除一个activity
     * @param clz
     */
    fun finishActivity(clz: Class<*>) {
        if (activityStack.size > 0) {
            for (i in activityStack.indices.reversed()) {
                val activity = activityStack[i]
                if (clz.name == activity.javaClass.name) {
                    activity.finish()
                    return
                }
            }
        }
    }

    /**
     * 移除一堆activity
     * @param clz
     */
    fun finishActivity(vararg clz: Class<*>?) {
        for (aClass in clz) {
            finishActivity(aClass)
        }
    }

    /**
     * finish指定的activity之上所有的activity
     *
     * @param actCls
     * @param isIncludeSelf 是否包含自己(自己是只当前界面,而不是指定的activity)
     * @return
     */
    fun finishToActivity(actCls: Class<out Activity?>?, isIncludeSelf: Boolean): Boolean {
        val buf: MutableList<Activity?> = ArrayList()
        val size = activityStack.size
        var activity: Activity? = null
        for (i in size - 1 downTo 0) {
            activity = activityStack[i]
            if (activity.javaClass.isAssignableFrom(actCls!!)) {
                for (a in buf) {
                    a!!.finish()
                }
                return true
            } else if (i == size - 1 && isIncludeSelf) {
                buf.add(activity)
            } else if (i != size - 1) {
                buf.add(activity)
            }
        }
        return false
    }

    /**
     * 结束全部activity
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity?.finish()
        }
        activityStack.clear()
    }

    /**
     * activity是否存在(目标页面是否打开)
     * @param cls
     * @return true 存在 false 不存在
     */
    fun activityExists(cls: Class<*>): Boolean {
        for (activity in activityStack) {
            if (activity.javaClass.simpleName == cls.simpleName) {
                return true
            }
        }
        return false
    }

    /**
     * 除开指定的activity之外其他的都finish
     */
    fun finishAllActivity(cls: Class<*>) {
        val closeActivity = ArrayList<Activity>()
        for (activity in activityStack) {
            if (activity.javaClass.simpleName != cls.simpleName) {
                closeActivity.add(activity)
            }
        }
        var i = 0
        val len = closeActivity.size
        while (i < len) {
            val activity = closeActivity[i]
            activity.finish()
            i++
        }
    }

    /**
     * 获取现在有多少个activity打开
     * @return
     */
    fun getPageSize(): Int {
        return try {
            activityStack.size
        } catch (e: Exception) {
            1
        }
    }

    /**
     * 跳转到下一个Activity
     */
    fun toNextActivity(clz: Class<*>?) {
        val lastActivity = getLastActivity() ?: return
        val intent = Intent(lastActivity, clz)
        lastActivity.startActivity(intent)
    }

    /**
     * 跳转到下一个Activity
     */
    fun toNextActivity(clz: Class<*>?, vararg datas: KeyValueEntity) {
        val lastActivity = getLastActivity() ?: return
        val intent = Intent(lastActivity, clz)
        try {
            var i = 0
            val len = datas.size
            while (i < len) {
                when (val value = datas[i].value) {
                    is Boolean -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Byte -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Char -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Short -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Int -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Long -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Float -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Double -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is String -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is CharSequence -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Parcelable -> {
                        intent.putExtra(datas[i].key, value)
                    }
                    is Serializable -> {
                        intent.putExtra(datas[i].key, value)
                    }
                }
                i++
            }
        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            lastActivity.startActivity(intent)
        }
    }

    /**
     * 退出应用程序
     */
    fun AppExit(context: Context?) {
        try {
            finishAllActivity()
        } catch (e: java.lang.Exception) {
            exitProcess(0)
        }
    }
}