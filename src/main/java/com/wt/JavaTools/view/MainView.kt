package com.wt.JavaTools.view

import tornadofx.*

/**
 * Created by wt on 2017/1/23.
 */
class MainView : View("EntityGenerator") {

    val m2jView: M2JView by inject()

    override val root = borderpane {
        minHeight = 450.0
        minWidth = 300.0
    }

    init {
        with(root) {
            center = m2jView.root
        }
    }

}