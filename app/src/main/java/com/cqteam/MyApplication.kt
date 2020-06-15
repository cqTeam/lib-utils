package com.cqteam

import android.app.Application
import com.cqteam.lib.utils.screen.CustomDensity

/**
 * Author： 洪亮
 * Time： 2020/6/15 - 10:53 AM
 * Email：281332545@qq.com
 * <p>
 * 描述：
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CustomDensity.init(this,375,667)
    }
}