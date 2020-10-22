package com.cqteam.lib.screen

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect

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

    fun getStatusBarHeight(activity: Activity): Int {
        val rectangle = Rect()
        val window = activity.window
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        return rectangle.top
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Int, activity: Activity): Int {
        return (dp * activity.resources.displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Float): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun pxToDp(px: Float): Int {
        val scale =
            Resources.getSystem().displayMetrics.density
        return (px / scale + 0.5).toInt()
    }

    /**
     * 将sp转换为px
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 将px转换为sp
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }
}