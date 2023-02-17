package com.demo.goriddles.ac

import com.demo.goriddles.R
import com.demo.goriddles.base.BaseAc
import com.demo.goriddles.conf.Local
import kotlinx.android.synthetic.main.activity_web.*

class WebAc:BaseAc(R.layout.activity_web) {
    override fun initView() {
        immersionBar.statusBarView(top_view).init()
        iv_back.setOnClickListener { finish() }
        web_view.apply {
            settings.javaScriptEnabled=true
            loadUrl(Local.WEB)
        }
    }
}