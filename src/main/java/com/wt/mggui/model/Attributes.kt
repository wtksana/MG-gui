package com.wt.mggui.model

/**
 * 实体类属性类
 * Created by wt on 2017/5/20.
 */
data class Attributes(
        //数据库字段
        val column: String,
        //类字段
        val property: String,
        //数据库字段类型
        val jdbcType: String,
        //java类型
        val javaType: String,
        //数据库字段备注
        val comment: String,
        //字段首字母大写
        val propertyUpCase: String
)