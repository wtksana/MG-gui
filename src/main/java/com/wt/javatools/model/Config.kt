package com.wt.javatools.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

/**
 * Created by wt on 2017/5/20.
 */
class Config {
    val ipProperty = SimpleStringProperty()
    val dataBaseProperty = SimpleStringProperty()
    val rootUserProperty = SimpleStringProperty()
    val passwordProperty = SimpleStringProperty()
    val tableNameProperty = SimpleStringProperty()
    val packageNameProperty = SimpleStringProperty()
    val entityNameProperty = SimpleStringProperty()
}

class ConfigModel(val configs: Config) : ViewModel() {
    val ip = bind { configs.ipProperty }
    val dataBase = bind { configs.dataBaseProperty }
    val rootUser = bind { configs.rootUserProperty }
    val password = bind { configs.passwordProperty }
    val tableName = bind { configs.tableNameProperty }
    val packageName = bind { configs.packageNameProperty }
    val entityName = bind { configs.entityNameProperty }
}