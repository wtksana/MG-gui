package com.wt.javatools.view

import javafx.scene.control.TabPane
import tornadofx.*

/**
 * Created by wt on 2017/1/23.
 */
class MainView : View("JavaTool by wt") {

    private val generatorView: GeneratorView by inject()
    private val regExView: RegExView by inject()
    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab("Generator") {
            this += generatorView.root
        }
        tab("RegEx") {
            this += regExView.root
        }
    }

}