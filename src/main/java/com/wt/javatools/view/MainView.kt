package com.wt.javatools.view

import tornadofx.*

/**
 * Created by wt on 2017/1/23.
 */
class MainView : View("MG-gui by wt") {

    private val generatorView: GeneratorView by inject()
    override val root = generatorView.root

}