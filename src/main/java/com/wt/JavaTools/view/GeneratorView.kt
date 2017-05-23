package com.wt.JavaTools.view

import com.wt.JavaTools.model.Config
import com.wt.JavaTools.model.ConfigModel
import com.wt.JavaTools.util.Generator
import javafx.beans.property.SimpleStringProperty
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*

/**
 * Created by wt on 2017/5/20.
 */
class GeneratorView : View() {
    override val root = anchorpane {
        prefHeight = 400.0
        prefWidth = 300.0
    }
    val configForm = Form()
    val saveConfigView: SaveConfig by inject()
    val configs = ConfigModel(Config())
    val output = SimpleStringProperty()

    init {
        initConfigForm()
        with(root) {
            this += configForm
        }
    }

    private fun initConfigForm() {
        with(configForm) {
            fieldset {
                field("ip") {
                    textfield(configs.ip) {
                        text = "127.0.0.1"
                        promptText = "请输入要连接的数据库IP"
                        required()
                    }
                }
                field("dataBase") {
                    textfield(configs.dataBase) {
                        text = ""
                        promptText = "请输入要连接的数据库名"
                        required()
                    }
                }
                field("rootUser") {
                    textfield(configs.rootUser) {
                        text = "root"
                        promptText = "请输入要连接的数据库root用户名"
                        required()
                    }
                }
                field("password") {
                    textfield(configs.password) {
                        text = "root"
                        promptText = "请输入要连接的数据库root用户密码"
                        required()
                    }
                }
                field("tableName") {
                    textfield(configs.tableName) {
                        text = ""
                        promptText = "请输入表名"
                        required()
                    }
                }
                field("entityName") {
                    textfield(configs.entityName) {
                        text = ""
                        promptText = "请输入要生成的实体类名"
                        required()
                    }
                }
                field("packageName") {
                    textfield(configs.packageName) {
                        text = ""
                        promptText = "请输入包路径(如com.mysql.jdbc)"
                        required()
                    }
                }
                field("操作") {
                    button("OK") {
                        setOnAction {
                            generate()
                        }
                    }
                    button("保存配置") {
                        setOnAction {
                            saveConfigs()
                        }
                    }
                    button("读取配置") {
                        setOnAction {
                            loadConfigs()
                        }
                    }
                }
            }
            textarea {
                isWrapText = true
                prefWidth = 300.0
                prefHeight = 50.0
                bind(output)
                isEditable = false
            }
        }
    }

    private fun loadConfigs() {
        LoadConfig().openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, primaryStage)
    }

    private fun saveConfigs() {
        saveConfigView.openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, primaryStage)
    }

    private fun generate() {
        if (configs.commit()) {
            runAsync {
                Generator.generate(configs.configs)
            } ui { rst ->
                output.value = rst
            }
        }
    }
}