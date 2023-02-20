package com.demo.goriddles.ac

import com.demo.goriddles.R
import com.demo.goriddles.admob.ShowNativeAd
import com.demo.goriddles.base.BaseAc
import com.demo.goriddles.conf.Local
import com.demo.goriddles.util.AdShowed
import kotlinx.android.synthetic.main.activity_result.*


class ResultAc:BaseAc(R.layout.activity_result) {
    private val showNativeAd by lazy { ShowNativeAd(Local.RESULT,this) }

    override fun initView() {
        immersionBar.statusBarView(top_view).init()
        iv_back.setOnClickListener { finish()}
        val connect = intent.getBooleanExtra("connect", false)
        iv_status.isSelected=connect
        tv_result.text=if (connect) "Connected succeeded" else "Disconnected succeeded"
    }

    override fun onResume() {
        super.onResume()
        if (!AdShowed.adShowed(Local.RESULT)){
            showNativeAd.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        showNativeAd.endShow()
        AdShowed.setShowed(Local.RESULT,false)
    }
}