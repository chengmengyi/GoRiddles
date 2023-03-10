package com.demo.goriddles.util

import com.tencent.mmkv.MMKV
import java.text.SimpleDateFormat
import java.util.*

object AdShowed {
    private var click=0
    private var show=0

    var maxClick=1
    var maxShow=10

    var firstLoad=true


    private val map= hashMapOf<String,Boolean>()

    fun adShowed(type:String)=map[type]?:false

    fun setShowed(type: String,boolean: Boolean){
        map[type]=boolean
    }

    fun reset(){
        map.clear()
    }

    fun setMax(click:Int,show:Int){
        maxClick=click
        maxShow=show
    }

    fun limit() = click >= maxClick||show >= maxShow

    fun addClick(){
        click++
        MMKV.defaultMMKV().encode(numKey("flash_click"), click)
    }

    fun addShow(){
        show++
        MMKV.defaultMMKV().encode(numKey("flash_show"), show)
    }

    fun readLocalNum(){
        click= MMKV.defaultMMKV().decodeInt(numKey("flash_click"),0)
        show= MMKV.defaultMMKV().decodeInt(numKey("flash_show"),0)
    }

    private fun numKey(key:String)="${SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis()))}_$key"
}