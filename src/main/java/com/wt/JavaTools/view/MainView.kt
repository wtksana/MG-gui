package com.wt.JavaTools.view

import javafx.scene.control.TabPane
import tornadofx.View
import tornadofx.plusAssign
import tornadofx.tab
import tornadofx.tabpane

/**
 * Created by wt on 2017/1/23.
 */
class MainView : View("JavaTools") {

    val m2jView: M2JView by inject()
    val projectCreatorView: ProjectCreatorView by inject()

    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab("M2J") {
            this += m2jView.root
        }
        tab("ProjectCreator") {
            this += projectCreatorView.root
        }
    }

}