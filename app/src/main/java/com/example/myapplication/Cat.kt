package com.example.myapplication

import android.util.Log

class Cat(
    val name: String,
    val age: Int,
    val gender: String,
    val breed: String // breed: 品種
) {
    fun say(message: String) {
        Log.d("Cat", "$name「$message」")
    }

    fun sleep() {
        say("Zzz")
    }
}