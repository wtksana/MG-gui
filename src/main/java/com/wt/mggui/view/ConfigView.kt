package com.wt.mggui.view

import com.wt.mggui.util.PropertiesUtil
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

/**
 * Created by wt on 2017/5/21.
 */
class SaveConfig : View() {
    override val root = Form()
    private val configName = SimpleStringProperty()
    private val generatorView: GeneratorView by inject()

    init {
        title = "保存配置"
        with(root) {
            fieldset {
                field("配置名") {
                    textfield {
                        bind(configName)
                    }
                }
            }
            button("保存") {
                setOnAction {
                    runAsync {
                        PropertiesUtil.saveProperty(generatorView.configs, configName.value)
                    } ui { rst ->
                        generatorView.output.value = rst
                        close()
                    }
                }
            }
        }
    }
}

class LoadConfig : Fragment() {
    override val root = listview<String> {
        prefHeight = 300.0
    }
    private val generatorView: GeneratorView by inject()

    init {
        title = "读取配置"
        with(root) {
            runAsync {
                PropertiesUtil.getProperties()
            } ui { rst ->
                if (rst.isNotEmpty()) {
                    items = rst.observable()
                }
            }
            cellFormat {
                text = it
                onDoubleClick {
                    runAsync {
                        PropertiesUtil.loadProperty(it)
                    } ui { rst ->
                        generatorView.configs.ip.value = rst.getProperty("ip").orEmpty()
                        generatorView.configs.dataBase.value = rst.getProperty("dataBase").orEmpty()
                        generatorView.configs.rootUser.value = rst.getProperty("rootUser").orEmpty()
                        generatorView.configs.password.value = rst.getProperty("password").orEmpty()
                        generatorView.configs.tableName.value = rst.getProperty("tableName").orEmpty()
                        generatorView.configs.entityName.value = rst.getProperty("entityName").orEmpty()
                        generatorView.configs.entityPackage.value = rst.getProperty("entityPackage").orEmpty()
                        generatorView.configs.mapperPackage.value = rst.getProperty("mapperPackage").orEmpty()
                        generatorView.configs.servicePackage.value = rst.getProperty("servicePackage").orEmpty()
                        close()
                    }
                }
            }
        }

    }
}