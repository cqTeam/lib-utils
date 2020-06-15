package com.cqteam.utils

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cqteam.lib.utils.screen.CustomDensity
import com.cqteam.lib.utils.screen.ScreenUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initCustomDensity()
        setContentView(R.layout.activity_main)
        Log.e("CustomDensity","Activity的数据的数据：density = ${ resources.displayMetrics.density}，" +
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
    }
}
