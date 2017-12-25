package com.wt.javatools.model

/**
 * Created by wt on 2017/5/20.
 */
data class Property(
        var entityPackage: String = "",
        var mapperPackage: String = "",
        var entityName: String = "",
        var entityNameLowCase: String = "",
        var tableName: String = "",
        var attrs: MutableList<Attributes> = mutableListOf(),
        var imports: MutableSet<String> = mutableSetOf(),
        var msg: String = ""
)