package com.demo.goriddles.conf
import android.util.Log
import com.demo.goriddles.myApp
import com.demo.goriddles.util.getAndroidId
import com.demo.goriddles.util.logGo
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import java.util.*

object ServerOkManager {
    private const val url="http://cloudserverhub.com/"


    fun getServerList(){

//        OkGo.get<String>("${url}api/server/list/")
//            .headers("ctr", Locale.getDefault().country)
//            .headers("pkg",myApp.packageName)
//            .headers("dev",getAndroidId(myApp))
//            .execute(object : StringCallback(){
//                override fun onSuccess(response: Response<String>?) {
//                    logGo("==onSuccess===${base64Body(response?.body()?.toString()?:"")}=")
//
//                }
//
//                override fun onError(response: Response<String>?) {
//                    super.onError(response)
//                    Log.e("qwer","=onError===${response?.body()?.toString()}=")
//                }
//            })
    }

    private fun base64Body(string: String):String=String(android.util.Base64.decode(StringBuffer(string).reverse().toString(),android.util.Base64.DEFAULT))
}