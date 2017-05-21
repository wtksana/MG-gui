package com.wt.JavaTools.model

/**
 * Created by wt on 2017/5/20.
 */
data class Property(var entityName: String = "",
                    var packageName: String = "",
                    var attrs: MutableList<Attribute> = arrayListOf(),
                    var msg: String = ""
)