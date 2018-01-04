package com.wt.mggui.view

import com.wt.mggui.model.Config
import com.wt.mggui.model.ConfigModel
import com.wt.mggui.util.Generator
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
    private val configForm = Form()
    private val saveConfigView: SaveConfig by inject()
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
                field("host") {
                    textfield(configs.ip) {
                        text = "127.0.0.1:3306"
                        promptText = "请输入要连接的数据库host"
                        required()
                    }
                }
                field("user") {
                    textfield(configs.rootUser) {
                        text = "root"
                        promptText = "请输入要连接的数据库的用户名"
                        required()
                    }
                }
                field("password") {
                    textfield(configs.password) {
                        text = "root"
                        promptText = "请输入要连接的数据库的用户密码"
                        required()
                    }
                }
                field("dataBase") {
                    textfield(configs.dataBase) {
                        text = "schemaName"
                        promptText = "请输入要连接的数据库名"
                        required()
                    }
                }
                field("tableName") {
                    textfield(configs.tableName) {
                        text = "tableName"
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
                field("entityPackage") {
                    textfield(configs.entityPackage) {
                        text = ""
                        promptText = "实体类包路径(如com.mggui.model)"
                        required()
                    }
                }
                field("mapperPackage") {
                    textfield(configs.mapperPackage) {
                        text = ""
                        promptText = "dao接口包路径(如com.mggui.dao)"
                        required()
                    }
                }
                field("servicePackage") {
                    textfield(configs.servicePackage) {
                        text = ""
                        promptText = "service接口包路径(如com.mggui.service)"
                    }
                }
                field("操作") {
                    button("RUN") {
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
                promptText = "错误信息..."
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