package com.cqteam.lib.utils

import android.widget.Toast
import com.cqteam.lib.UtilsManager

/**
 *
 * @Description:    吐司工具类
 * @Author:         koloces
 * @CreateDate:     2020/6/17 10:05
 */
object ToastUtils {
    private val notToast: MutableList<String> by lazy { mutableListOf<String>() }

    fun toast(str: String) {
        if (StringUtils.isEmpty(str)) {
            return
        }
        for (s in notToast) {
            if (str.contains(s)) {
                return
            }
        }
        if (UtilsManager.getContext() == null) {
            return
        }
        Toast.makeText(UtilsManager.getContext(),str,Toast.LENGTH_LONG).show()
    }

    /**
     * 添加不用显示的吐司的文字(有时候可能有用)
     * @param notToastStrs
     */
    fun putNotToastStr(vararg notToastStrs :String) {
        notToast.addAll(notToastStrs)
    }

    /**
     * 添加不用显示的吐司的文字(有时候可能有用)
     * @param str
     */
    fun putNotToastStr(str: String?) {
        if (StringUtils.isEmpty(str)) return
        notToast.add(str!!)
    }
}