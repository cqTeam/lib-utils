package com.cqteam.utils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.cqteam.lib.utils.screen.CustomDensity
import kotlinx.android.synthetic.main.activity_use_custom_density.*

class UseCustomDensityActivity : BaseActivity() {

    override fun setStandard(): CustomDensity.Standard {
        return CustomDensity.Standard.Height
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_custom_density)
        Log.e("CustomDensity","Activity的数据：density = ${ resources.displayMetrics.density}，" +
                "scaledDensity = ${ resources.displayMetrics.scaledDensity}，" +
                "densityDpi = ${ resources.displayMetrics.densityDpi}")

        btn.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java) )
        }

        btn.text = "Activity的数据：density = ${ resources.displayMetrics.density}，" +
                "scaledDensity = ${ resources.displayMetrics.scaledDensity}，" +
                "densityDpi = ${ resources.displayMetrics.densityDpi}"
    }

    override fun onResume() {
        super.onResume()
        Log.e("lif","onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.e("lif","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("lif","onDestroy")
    }
}