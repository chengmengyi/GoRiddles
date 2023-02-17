package com.demo.goriddles.util

object AdShowed {

    private val map= hashMapOf<String,Boolean>()

    fun adShowed(type:String)=map[type]?:false

    fun setShowed(type: String,boolean: Boolean){
        map[type]=boolean
    }

    fun reset(){
        map.clear()
    }
}