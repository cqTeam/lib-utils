package com.cqteam.lib.screen

import android.content.Context

/**
 * Author： 洪亮
 * Time： 2020/6/15 - 11:22 AM
 * Email：281332545@qq.com
 * <p>
 * 描述：
 */
object ScreenUtils{

    fun getWidthPixels(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getHeightPixels(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }
}