package com.cqteam.lib.screen

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import com.cqteam.lib.utils.LogUtils

/**
 * Author： 洪亮
 * Time： 2020/4/13 - 10:57 AM
 * Email：281332545@qq.com
 * <p>
 * 描述：今日头条的适配方法
 *  https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 * <p>
 */
class CustomDensity {

    enum class Standard { Width, Height }

    companion object {
        var mTargetDensity = 0f
        var mTargetScaledDensity = 0f

        /**
         *  UI设计图的宽度基准，单位dp
         */
        private var uiWidthStandard: Int = 0

        /**
         *  UI设计图的高度基准，单位dp
         */
        private var uiHeightStandard: Int = 0

        /**
         *  标志是宽度标准还是高度标准，默认是宽度标准
         */
        private lateinit var whichStandard: Standard

        // 设置字体的
        private var sNonCompatDensity: Float = 0f
        private var sNonCompatScaledDensity: Float = 0f

        private lateinit var mApplication: Application

        /**
         *  初始化，建议在 Application 中完成<br/>
         *  @param application 程序的Application, 建议在 {@link android.app.Application#onCreate()} 完成
         *  @param uiWidthDp: UI设计图的宽，单位是dp，即密度无关像素
         *  @param uiHeightDp: UI设计图的高，单位是dp，即密度无关像素
         */
        fun init(application: Application, uiWidthDp: Int, uiHeightDp: Int, standard: Standard = Standard.Width) {
            mApplication = application
            uiWidthStandard = uiWidthDp
            uiHeightStandard = uiHeightDp
            whichStandard = standard
        }

        /**
         *  在每个Activity中设置，建议在 setContentView() 之前使用
         *  @param activity 需要使用自定义密度的 Activity
         */
        fun changeStandard(activity: Activity,standard: Standard) {
            if (standard == whichStandard) return
            whichStandard = standard
            setActivityCustomDensity(activity)
        }

        fun setActivityCustomDensity(activity: Activity) {
            when(whichStandard) {
                Standard.Width -> setCustomDensityByWidth(activity)
                Standard.Height -> setCustomDensityByHeight(activity)
            }
        }

        private fun setCustomDensityByWidth(activity: Activity) {
            val appDisplayMetrics = mApplication.resources.displayMetrics
            LogUtils.e("系统数据：density = ${appDisplayMetrics.density}，" +
                    "scaledDensity = ${appDisplayMetrics.scaledDensity}，" +
                    "densityDpi = ${appDisplayMetrics.densityDpi}")

            // 设置字体
            if (sNonCompatDensity == 0f) {
                sNonCompatDensity = appDisplayMetrics.density
                sNonCompatScaledDensity = appDisplayMetrics.scaledDensity
                mApplication.registerComponentCallbacks(object : ComponentCallbacks{
                    override fun onLowMemory() {}

                    override fun onConfigurationChanged(newConfig: Configuration) {
                        if (newConfig.fontScale > 0) {
                            sNonCompatScaledDensity = mApplication.resources.displayMetrics.scaledDensity
                        }
                    }
                })
            }

            val targetDensity: Float = (appDisplayMetrics.widthPixels.toFloat() / uiWidthStandard)
            val targetDensityDpi = (160 * targetDensity).toInt()
            val targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity)

            LogUtils.e("计算出来的数据：density = $targetDensity，" +
                    "scaledDensity = $targetScaledDensity，" +
                    "densityDpi = $targetDensityDpi")

            appDisplayMetrics.density = targetDensity
            appDisplayMetrics.scaledDensity = targetScaledDensity
            appDisplayMetrics.densityDpi = targetDensityDpi

            val activityDisplayMetrics = activity.resources.displayMetrics
            activityDisplayMetrics.density = targetDensity
            activityDisplayMetrics.scaledDensity = targetScaledDensity
            activityDisplayMetrics.densityDpi = targetDensityDpi

            mTargetDensity = targetDensity
            mTargetScaledDensity = targetScaledDensity

            LogUtils.e("计算之后系统的数据：density = ${appDisplayMetrics.density}，" +
                    "scaledDensity = ${appDisplayMetrics.scaledDensity}，" +
                    "densityDpi = ${appDisplayMetrics.densityDpi}")
        }

        private fun setCustomDensityByHeight(activity: Activity) {
            val appDisplayMetrics = mApplication.resources.displayMetrics
            LogUtils.e("系统数据：density = ${appDisplayMetrics.density}，" +
                    "scaledDensity = ${appDisplayMetrics.scaledDensity}，" +
                    "densityDpi = ${appDisplayMetrics.densityDpi}")

            // 设置字体
            if (sNonCompatDensity == 0f) {
                sNonCompatDensity = appDisplayMetrics.density
                sNonCompatScaledDensity = appDisplayMetrics.scaledDensity
                mApplication.registerComponentCallbacks(object : ComponentCallbacks{
                    override fun onLowMemory() {}

                    override fun onConfigurationChanged(newConfig: Configuration) {
                        if (newConfig.fontScale > 0) {
                            sNonCompatScaledDensity = mApplication.resources.displayMetrics.scaledDensity
                        }
                    }
                })
            }

            val targetDensity: Float = (appDisplayMetrics.heightPixels.toFloat() / uiHeightStandard)
            val targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity)
            val targetDensityDpi = (160 * targetDensity).toInt()

            appDisplayMetrics.density = targetDensity
            appDisplayMetrics.scaledDensity = targetScaledDensity
            appDisplayMetrics.densityDpi = targetDensityDpi

            val activityDisplayMetrics = activity.resources.displayMetrics
            activityDisplayMetrics.density = targetDensity
            activityDisplayMetrics.scaledDensity = targetScaledDensity
            activityDisplayMetrics.densityDpi = targetDensityDpi

            LogUtils.e("计算之后系统的数据：density = ${appDisplayMetrics.density}，" +
                    "scaledDensity = ${appDisplayMetrics.scaledDensity}，" +
                    "densityDpi = ${appDisplayMetrics.densityDpi}")
        }
    }
}