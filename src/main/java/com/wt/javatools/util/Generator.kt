package com.wt.javatools.util

import com.wt.javatools.model.Attribute
import com.wt.javatools.model.Config
import com.wt.javatools.model.Property
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
            val paths = getFilePath(property.entityName)
            for ((key, value) in paths) {
                val temp = cfg.getTemplate("$key.ftl")
                val dir = File("generator/$value")
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val osp: FileOutputStream
                osp = if (key == "Domain") {
                    FileOutputStream(File(dir, "${property.entityName}.java"))
                } else {
                    FileOutputStream(File(dir, "${property.entityName}$key.java"))
                }
                val out = OutputStreamWriter(osp)
                temp.process(property, out)
                osp.flush()
                osp.close()
            }
            return "操作成功"
        } catch(e: Exception) {
            return e.message.orEmpty()
        }
    }

    private fun getFilePath(name: String): Map<String, String> {
        return hashMapOf("Domain" to "$name/domain", "Service" to "$name/service", "ServiceImpl" to "$name/service/impl", "Dao" to "$name/dao", "DaoImpl" to "$name/dao/impl")
    }

    private fun getProperties(configs: Config): Property {
        val property = Property()
        property.entityName = configs.entityNameProperty.value
        property.entityNameLowCase = (property.entityName[0] + 32) + property.entityName.substring(1)
        property.packageName = configs.packageNameProperty.value
        property.tableName = configs.tableNameProperty.value
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
                val type = if (dataType == "decimal") {
                    "double"
                } else if (dataType == "int" || dataType == "tinyint") {
                    "int"
                } else if (dataType == "date" || dataType == "datetime") {
                    "Date"
                } else if (dataType == "bigint" || dataType == "timestamp") {
                    "long"
                } else {
                    "String"
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
        return columnName.toLowerCase().split("_").dropLastWhile(String::isEmpty).map { (it[0] - 32) + it.substring(1) }.joinToString("")
    }


}