package com.demo.goriddles.ac

import com.demo.goriddles.R
import com.demo.goriddles.base.BaseAc
import kotlinx.android.synthetic.main.activity_result.*


class ResultAc:BaseAc(R.layout.activity_result) {

    override fun initView() {
        immersionBar.statusBarView(top_view).init()
        iv_back.setOnClickListener { finish()}
        val connect = intent.getBooleanExtra("connect", false)
        iv_status.isSelected=connect
        tv_result.text=if (connect) "Connected succeeded" else "Disconnected succeeded"
    }
}