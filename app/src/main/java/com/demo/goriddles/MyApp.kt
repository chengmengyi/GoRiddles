package com.demo.goriddles

import android.app.ActivityManager
import android.app.Application
import com.demo.goriddles.ac.HomeAc
import com.demo.goriddles.conf.Fire
import com.demo.goriddles.util.AcRegister
import com.github.shadowsocks.Core
import com.tencent.mmkv.MMKV

lateinit var myApp: MyApp
class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        myApp=this
        MMKV.initialize(this)
        Core.init(this,HomeAc::class)
        if (!packageName.equals(processName(this))){
            return
        }
        AcRegister.register(this)
        Fire.readFire()
    }

    private fun processName(applicationContext: Application): String {
        val pid = android.os.Process.myPid()
        var processName = ""
        val manager = applicationContext.getSystemService(Application.ACTIVITY_SERVICE) as ActivityManager
        for (process in manager.runningAppProcesses) {
            if (process.pid === pid) {
                processName = process.processName
            }
        }
        return processName
    }
}