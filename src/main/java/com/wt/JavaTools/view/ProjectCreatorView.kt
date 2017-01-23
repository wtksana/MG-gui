package com.wt.JavaTools.view

import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.FlowPane
import tornadofx.View
import tornadofx.getChildList

/**
 * Created by wt on 2017/1/22.
 */
class ProjectCreatorView : View() {
    override val root: AnchorPane by fxml("/fxml/ProjectCreatorView.fxml")
    val generate: ChoiceBox<String> by fxid("generate")
    val packaging: ChoiceBox<String> by fxid("packaging")
    val javaVersion: ChoiceBox<String> by fxid("javaVersion")
    val group: TextField by fxid("group")
    val artifact: TextField by fxid("artifact")
    val version: TextField by fxid("version")
    val packageName: TextField by fxid("packageName")
    val done: Button by fxid("done")
    val dependencies: FlowPane by fxid("dependencies")

    init {
        done.setOnMouseReleased { create() }
        group.focusedProperty().addListener { observableValue, old, new ->
            if (!new) {
                packageName.text = group.text + "." + artifact.text
            }
        }
        artifact.focusedProperty().addListener { observableValue, old, new ->
            if (!new) {
                packageName.text = group.text + "." + artifact.text
            }
        }
    }

    private fun create() {
        println(generate.value)
        val dependencies = dependencies.getChildList().orEmpty().filter { it is CheckBox }.filter { (it as CheckBox).isSelected }
        println(dependencies)
    }
}