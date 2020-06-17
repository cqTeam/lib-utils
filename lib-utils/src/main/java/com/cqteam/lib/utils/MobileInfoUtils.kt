package com.cqteam.lib.utils

import android.os.Build
import java.util.*

/**
 *
 * @Description:    获取手机基本信息工具类
 * @Author:         koloces
 * @CreateDate:     2020/6/17 10:30
 */
object MobileInfoUtils {
    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    fun getDeviceBrand(): String? {
        return Build.BRAND
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    fun getSystemModel(): String? {
        return Build.MODEL
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    fun getSystemVersion(): String? {
        return Build.VERSION.RELEASE
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    fun getSystemLanguageList(): Array<Locale?>? {
        return Locale.getAvailableLocales()
    }

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    fun getSystemLanguage(): String? {
        return Locale.getDefault().language
    }
}