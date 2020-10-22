package com.cqteam.lib

import android.content.Context
import com.cqteam.lib.utils.LogUtils
import com.cqteam.lib.utils.sp.SP

/**
 *
 * @Description:    Utils管理类
 * @Author:         koloces
 * @CreateDate:     2020/6/17 9:48
 */
object UtilsManager {
    private lateinit var mContext: Context
    private var isDebug: Boolean = false

    fun init(context: Context,isDebug:Boolean){
        this.mContext = context
        this.isDebug = isDebug
        SP.init(context)
        LogUtils.init(isDebug)
    }

    fun getContext() : Context{
        return mContext
    }
    fun isDebug() : Boolean{
        return isDebug
    }

}