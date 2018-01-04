package com.wt.mggui.model

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
    val entityNameProperty = SimpleStringProperty()
    val entityPackageProperty = SimpleStringProperty()
    val mapperPackageProperty = SimpleStringProperty()
    val servicePackageProperty = SimpleStringProperty()
}

class ConfigModel(val configs: Config) : ViewModel() {
    val ip = bind { configs.ipProperty }
    val dataBase = bind { configs.dataBaseProperty }
    val rootUser = bind { configs.rootUserProperty }
    val password = bind { configs.passwordProperty }
    val tableName = bind { configs.tableNameProperty }
    val entityName = bind { configs.entityNameProperty }
    val entityPackage = bind { configs.entityPackageProperty }
    val mapperPackage = bind { configs.mapperPackageProperty }
    val servicePackage = bind { configs.servicePackageProperty }
}