package com.wt.javatools.model

/**
 * Created by wt on 2017/5/20.
 */
data class Property(
        var entityPackage: String = "",
        var mapperPackage: String = "",
        var entityName: String = "",
        var entityNameLowCase: String = "",
        var packageName: String = "",
        var tableName: String = "",
        var attrs: MutableList<Attribute> = arrayListOf(),
        var msg: String = ""
)