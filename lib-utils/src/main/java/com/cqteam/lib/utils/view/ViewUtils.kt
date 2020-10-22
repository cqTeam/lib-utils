package com.cqteam.lib.utils.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup

/**
 *
 * @Description:
 * @Author:         koloces
 * @CreateDate:     2020/10/22 14:14
 */
object ViewUtils {
    /**
     * 把某个控件的UI转换成bitmap
     */
    private fun getViewBitmap(view: View): Bitmap? {
        if (null != view) {
            var height = 0
            //正确获取ScrollView
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    height += view.getChildAt(i).height
                }
            } else {
                height += view.height
            }
            if (height > 0) {
                //创建保存缓存的bitmap
                val bitmap =
                    Bitmap.createBitmap(view.width, height, Bitmap.Config.RGB_565)
                //可以简单的把Canvas理解为一个画板 而bitmap就是块画布
                val canvas = Canvas(bitmap)
                //把view的内容都画到指定的画板Canvas上
                view.draw(canvas)
                return bitmap
            }
        }
        return null
    }

}