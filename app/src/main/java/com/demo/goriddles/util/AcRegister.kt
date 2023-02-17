package com.demo.goriddles.util

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.blankj.utilcode.util.ActivityUtils
import com.demo.goriddles.ac.HomeAc
import com.demo.goriddles.ac.MainActivity
import com.google.android.gms.ads.AdActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AcRegister {
    var goFront=true
    private var goJob:Job?=null
    private var reload=false

    fun register(application: Application){
        application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
            private var pages=0
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

            override fun onActivityStarted(activity: Activity) {
                pages++
                goJob?.cancel()
                goJob=null
                if (pages==1){
                    goFront=true
//                    if (reload){
//                        if (ActivityUtils.isActivityExistsInStack(HomeAc::class.java)){
//                            activity.startActivity(Intent(activity, MainActivity::class.java))
//                        }
//                    }
                    reload=false
                }
            }

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {
                pages--
                if (pages<=0){
                    goFront=false
                    goJob= GlobalScope.launch {
                        delay(3000L)
                        reload=true
//                        ActivityUtils.finishActivity(MainPage::class.java)
//                        ActivityUtils.finishActivity(AdActivity::class.java)
                    }
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {}
        })
    }
}