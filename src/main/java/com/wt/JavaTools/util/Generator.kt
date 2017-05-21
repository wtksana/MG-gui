package com.wt.JavaTools.util

import com.wt.JavaTools.model.Attribute
import com.wt.JavaTools.model.Config
import com.wt.JavaTools.model.Property
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

/**
 * Created by wt on 2017/5/20.
 */
object Generator {

    fun generate(configs: Config): String {
        val property = getProperties(configs)
        return generate(property)
    }

    private fun generate(property: Property): String {
        try {
            val cfg = Configuration(Configuration.VERSION_2_3_23)
            cfg.defaultEncoding = "UTF-8"
            cfg.setDirectoryForTemplateLoading(File(this.javaClass.classLoader.getResource("ftl").path))
            cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER

            val temp = cfg.getTemplate("entity.ftl")

            val dir = File("generator")
            if (!dir.exists()) {
                dir.mkdir()
            }
            val osp = FileOutputStream(File(dir, property.entityName + ".java"))
            val out = OutputStreamWriter(osp)
            temp.process(property, out)
            osp.flush()
            osp.close()
            return "操作成功"
        } catch(e: Exception) {
            return e.message.orEmpty()
        }
    }

    fun getProperties(configs: Config): Property {
        val property = Property()
        property.entityName = configs.entityNameProperty.value
        property.packageName = configs.packageNameProperty.value
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            val con: Connection = DriverManager.getConnection("jdbc:mysql://${configs.ipProperty.value}/${configs.dataBaseProperty.value}?useUnicode=true&characterEncoding=utf8&useSSL=false", configs.rootUserProperty.value, configs.passwordProperty.value)
            val stmt: Statement = con.createStatement()
            val sql = "SELECT COLUMN_NAME, COLUMN_COMMENT, DATA_TYPE FROM information_schema.COLUMNS WHERE TABLE_NAME = '${configs.tableNameProperty.value}' AND TABLE_SCHEMA = '${configs.dataBaseProperty.value}'"
            println(sql)
            val rs: ResultSet = stmt.executeQuery(sql)
            while (rs.next()) {
                val columnName = rs.getString("COLUMN_NAME")
                val dataType = rs.getString("DATA_TYPE")
                val comments = rs.getString("COLUMN_COMMENT")
                val fieldNameUpCase = getFieldNameUpCase(columnName)
                val fieldName = (fieldNameUpCase[0] + 32) + fieldNameUpCase.substring(1)
                val type: String
                if (dataType == "decimal") {
                    type = "double"
                } else if (dataType == "int" || dataType == "tinyint") {
                    type = "int"
                } else if (dataType == "date" || dataType == "datetime") {
                    type = "Date"
                } else if (dataType == "bigint" || dataType == "timestamp") {
                    type = "long"
                } else {
                    type = "String"
                }
                property.attrs.add(Attribute(fieldName, type, comments, fieldNameUpCase))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return property
    }

    private fun getFieldNameUpCase(columnName: String): String {
        //将字段首字母大写
        val fieldName = columnName.toLowerCase().split("_").dropLastWhile(String::isEmpty).map { (it[0] - 32) + it.substring(1) }.joinToString("")
        return fieldName
    }


}