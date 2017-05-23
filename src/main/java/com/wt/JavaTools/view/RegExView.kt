package com.wt.JavaTools.view

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation.VERTICAL
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

/**
 * Created by wt on 2017/5/23.
 */
class RegExView : View() {
    override val root = Form()
    val regex = SimpleStringProperty()

    init {
        fieldset {
            labelPosition = VERTICAL
            field("待匹配文本") {
                textarea {
                    isWrapText = true
                    prefHeight = 50.0
                    prefWidth = 300.0
                }
            }
            field("常用") {
                label("邮箱") {
                    font = Font.font(14.0)
                    isUnderline = true
                    textFill = Color.BLUE
                    setOnMouseEntered {
                        textFill = Color.DARKCYAN
                    }
                    setOnMouseExited {
                        textFill = Color.BLUE
                    }
                    setOnMouseClicked {
                        regex.value = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?"
                    }
                }
            }
            field("正则表达式") {
                textarea {
                    isWrapText = true
                    bind(regex)
                    prefHeight = 50.0
                    prefWidth = 300.0
                }
            }
            button("测试") {

            }
            field("匹配结果") {
                textarea {
                    isWrapText = true
                    prefHeight = 50.0
                    prefWidth = 300.0
                }
            }
        }
    }
}