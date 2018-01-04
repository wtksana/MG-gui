package com.wt.mggui.model

/**
 * Created by wt on 2017/5/20.
 */
data class Attributes(
        val column: String,
        val property: String,
        val jdbcType: String,
        val javaType: String,
        val comment: String,
        val propertyUpCase: String
)