package com.demo.goriddles.admob

import com.demo.goriddles.base.BaseAc
import com.demo.goriddles.conf.Local
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FullAdCallback(
    private val baseAc: BaseAc,
    private val type:String,
    private val close:()->Unit
): FullScreenContentCallback()  {

    override fun onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent()
        LoadAdImpl.showingFull=false
        onClose()
    }

    override fun onAdShowedFullScreenContent() {
        super.onAdShowedFullScreenContent()
        LoadAdImpl.showingFull=true
        LoadAdImpl.removeAd(type)
    }

    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
        super.onAdFailedToShowFullScreenContent(p0)
        LoadAdImpl.showingFull=false
        LoadAdImpl.removeAd(type)
        onClose()
    }

    private fun onClose(){
        if (type!= Local.OPEN){
            LoadAdImpl.load(type)
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(200L)
            if (baseAc.resume){
                close.invoke()
            }
        }
    }
}