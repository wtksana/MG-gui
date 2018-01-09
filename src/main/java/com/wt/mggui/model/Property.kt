package com.wt.mggui.model

/**
 * 构造属性 填充ftl文件
 * Created by wt on 2017/5/20.
 */
data class Property(
        //实体类包路径
        var entityPackage: String = "",
        //mapper类包路径
        var mapperPackage: String = "",
        //service类包路径
        var servicePackage: String = "",
        //实体类名
        var entityName: String = "",
        //实体类名小写
        var entityNameLowCase: String = "",
        //数据库表名
        var tableName: String = "",
        //类属性
        var attrs: MutableList<Attributes> = mutableListOf(),
        //需要import的包
        var imports: MutableSet<String> = mutableSetOf(),
        //其他
        var msg: String = ""
)