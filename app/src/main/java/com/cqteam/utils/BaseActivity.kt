package com.cqteam.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cqteam.lib.utils.screen.CustomDensity

/**
 * Author： 洪亮
 * Time： 2020/6/15 - 4:19 PM
 * Email：281332545@qq.com
 * <p>
 * 描述：
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CustomDensity.changeStandard(this,setStandard())
    }

    protected fun initCustomDensity() {
        CustomDensity.setActivityCustomDensity(this)
    }

    protected open fun setStandard(): CustomDensity.Standard {
        return CustomDensity.Standard.Width
    }
}