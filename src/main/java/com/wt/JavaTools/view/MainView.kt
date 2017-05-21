package com.wt.JavaTools.view

import tornadofx.*

/**
 * Created by wt on 2017/1/23.
 */
class MainView : View("EntityGenerator") {

    val generatorView: GeneratorView by inject()

    override val root = borderpane {
//        prefHeight = 400.0
//        prefWidth = 300.0
    }

    init {
        with(root) {
            center = generatorView.root
        }
    }

}