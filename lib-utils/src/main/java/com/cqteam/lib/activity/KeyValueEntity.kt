package com.cqteam.lib.activity

import java.io.Serializable

/**
 *
 * @Description:    Intent传递数据类
 * @Author:         koloces
 * @CreateDate:     2020/6/17 15:14
 */
class KeyValueEntity(key:String,vale : Any) : Serializable{
    var key : String = key
    var value : Any = vale
}