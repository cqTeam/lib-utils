package com.cqteam.lib.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @Description:    时间转换工具
 * @Author:         koloces
 * @CreateDate:     2020/6/17 10:44
 */
object TimeUtils {
    fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 获取今天，昨天，前天，等等
     *
     * @param otherTime 时间戳
     * @return
     */
    fun getTimeStr(otherTime: String): String? {
        return getTimeStr(otherTime.toLong())
    }

    fun getTimeStr(otherTime: String, format: String): String? {
        return getTimeStr(otherTime.toLong(), format)
    }

    fun getTimeStr(otherTime: Long): String? {
        return getTimeStr(otherTime, "yyyy-MM-dd HH:mm")
    }

    /**
     * 获取当前时间
     * 样式自己写:yyyy-MM-dd HH:mm:ss
     */
    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(format: String): String? {
        return SimpleDateFormat(format).format(getCurrentTime())
    }

    /**
     * SimpleDateFormat
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    fun getFormat(time: Long, format: String): String? {
        return SimpleDateFormat(format).format(time)
    }

    /**
     * SimpleDateFormat
     *
     * @return
     */
    fun getFormat(time: String, format: String): String? {
        return getFormat(time.toLong(),format)
    }

    /**
     * SimpleDateFormat
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    fun getFormat(time: String): String? {
        return getFormat(time,"yyyy-MM-dd HH:mm:ss")
    }

    /**
     * SimpleDateFormat
     *
     * @return
     */
    fun getFormat(time: Long): String? {
        return getFormat(time,"yyyy-MM-dd HH:mm:ss")
    }

    /**
     * 获取今天，昨天，前天，等等
     *
     * @param otherTime 时间戳
     * @return
     */
    fun getTimeStr(otherTime: Long, format: String): String? {
        var otherTime = otherTime
        if (otherTime.toString().length < 13) {
            otherTime *= 1000
        }
        val nowTime: Long = getCurrentTime()
        val difference = nowTime - otherTime
        if (difference < 1000 * 60 * 60) { //小于半小时
            var count = (difference / (1000 * 60)).toInt()
            if (count <= 0) {
                count = 1
            }
            return """${count}分钟前"""
        }
        if (difference < 1000 * 60 * 60 * 24) { //一天之内
            var count = (difference / (1000 * 60 * 60)).toInt()
            if (count <= 0) {
                count = 1
            }
            return count.toString() + "小时前"
        }
        if (difference < 1000 * 60 * 60 * 24 * 2) { //两天之内
            return "昨天"
        }
        return if (difference < 1000 * 60 * 60 * 24 * 3) { //两天之内
            "前天"
        } else getFormat(otherTime, format)
    }

    /*
     * 将时间转换为时间戳
     * "yyyy-MM-dd HH:mm:ss"
     */
    fun dateToStamp(time: String?, format: String?): String? {
        val simpleDateFormat = SimpleDateFormat(format)
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val ts = date!!.time
        return ts.toString()
    }
}