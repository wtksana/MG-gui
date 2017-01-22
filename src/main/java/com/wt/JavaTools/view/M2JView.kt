package com.wt.JavaTools.view

import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import tornadofx.View
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement


class M2JView : View("M2J") {
    override val root: VBox by fxml("/fxml/M2JView.fxml")
    val ip: TextField by fxid("ip")
    val dataBase: TextField by fxid("dataBase")
    val rootUser: TextField by fxid("rootUser")
    val password: TextField by fxid("password")
    val tableName: TextField by fxid("tableName")
    val done: Button by fxid("done")
    val output: TextArea by fxid("output")

    init {
        done.setOnMouseReleased { m2j() }
    }

    fun m2j() {
        if (ip.text.isBlank() || dataBase.text.isBlank() || rootUser.text.isBlank() || password.text.isBlank() || tableName.text.isBlank()) {
            output.text = "请输入数据库信息！"
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance()
                val con: Connection = DriverManager.getConnection("jdbc:mysql://${ip.text}/${dataBase.text}?useUnicode=true&characterEncoding=utf8&useSSL=false", rootUser.text, password.text)
                val stmt: Statement = con.createStatement()
                val sql = "SELECT COLUMN_NAME, COLUMN_COMMENT, DATA_TYPE FROM information_schema.COLUMNS WHERE TABLE_NAME = '${tableName.text}' AND TABLE_SCHEMA = '${dataBase.text}'"
                println(sql)
                val rs: ResultSet = stmt.executeQuery(sql)
                val sb = StringBuffer("")
                while (rs.next()) {
                    val columnName = rs.getString("COLUMN_NAME")
                    val dataType = rs.getString("DATA_TYPE")
                    val comments = rs.getString("COLUMN_COMMENT")
                    val name = getName(columnName, dataType, comments)
                    sb.append(name)
                }
                output.text = sb.toString()
            } catch (e: Exception) {
                output.text = e.message
            }
        }
    }

    private fun getName(columnName: String, dataType: String, comments: String): String {
        val sb = StringBuilder()

        val fieldNames = columnName.toLowerCase().split("_".toRegex()).dropLastWhile(String::isEmpty).toTypedArray()
        val type: String
        //将字段首字母大写
        for (fieldName in fieldNames) {
            val firstWord = fieldName.substring(0, 1).toUpperCase()
            sb.append(firstWord)
            sb.append(fieldName.substring(1, fieldName.length))
        }
        val head = sb.toString().substring(0, 1).toLowerCase()
        val tail = sb.substring(1, sb.length)
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
        //        String comment = "/**" + "\n" + " *" + comments + "\n */" + "\n";
        val comment = "/* $comments */\n"
        val property = "private $type $head$tail;\n"
        val name = comment + property
        return name
    }
}