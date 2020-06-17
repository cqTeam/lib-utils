package com.cqteam.utils

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.cqteam.lib.screen.ScreenUtils
import com.cqteam.lib.utils.LogUtils
import com.cqteam.lib.utils.SP
import com.cqteam.lib.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCustomDensity()
        setContentView(R.layout.activity_main)
        LogUtils.e("Activity的数据的数据：density = ${ resources.displayMetrics.density}，" +
                "scaledDensity = ${ resources.displayMetrics.scaledDensity}，" +
                "densityDpi = ${ resources.displayMetrics.densityDpi}")
        btn.setOnClickListener {
            startActivity(Intent(this,UseCustomDensityActivity::class.java) )
        }
        btn1.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java) )
        }
        btn.text = "${ScreenUtils.getWidthPixels(this)} X ${ScreenUtils.getHeightPixels(this)}"
        btn1.text = "Activity的数据的数据：density = ${ resources.displayMetrics.density}，" +
                "scaledDensity = ${ resources.displayMetrics.scaledDensity}，" +
                "densityDpi = ${ resources.displayMetrics.densityDpi}"

        spGet.setOnClickListener {
            val string = SP.getString("tag", "spTest", "空")
            LogUtils.e("Test","SP.GET--->$string")
        }
        spPut.setOnClickListener {
            val str = et.text.toString()
            SP.put("tag","spTest",str)
            LogUtils.e("Test","SP.PUT")
        }
        putNotToast.setOnClickListener {
            val str = et.text.toString()
            ToastUtils.putNotToastStr(str)
            LogUtils.e("putNotToast")
        }
        toast.setOnClickListener {
            val str = et.text.toString()
            ToastUtils.toast(str)
            LogUtils.e("toast")
        }
    }
}
