package com.wing.guess

import java.util.*

class SecretNumber {

    //不變的variable like `final` in java
    var secret: Int = Random().nextInt(10)+1 // 1 to 10
    //可變的variable
    var count: Int = 0

    fun validate(number: Int): Int{
        count++
        return number - secret
    }

    fun reset(){
        secret = Random().nextInt(10)+1 // 1 to 10
        count = 0
    }
}

fun main() {
    val secretNumber = SecretNumber()
}