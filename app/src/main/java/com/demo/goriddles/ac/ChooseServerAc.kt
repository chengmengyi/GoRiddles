package com.demo.goriddles.ac

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.goriddles.R
import com.demo.goriddles.adapter.ServerAdapter
import com.demo.goriddles.base.BaseAc
import com.demo.goriddles.bean.ServerBean
import com.demo.goriddles.server.ConnectServer
import kotlinx.android.synthetic.main.activity_choose_server.*

class ChooseServerAc:BaseAc(R.layout.activity_choose_server) {
    private val myAdapter by lazy { ServerAdapter(this){ click(it) } }

    override fun initView() {
        immersionBar.statusBarView(top_view).init()
        iv_back.setOnClickListener { onBackPressed() }


        server_list.apply {
            layoutManager=LinearLayoutManager(this@ChooseServerAc)
            adapter=myAdapter
        }
    }

    private fun click(serverBean: ServerBean){
        val currentServer = ConnectServer.currentServer
        val connected = ConnectServer.isConnected()
        if (connected&&currentServer.goRi_ip!=serverBean.goRi_ip){
            showDialog { chooseServer("disconnect",serverBean) }
        }else{
            if (connected){
                chooseServer("",serverBean)
            }else{
                chooseServer("connect",serverBean)
            }
        }
    }


    private fun chooseServer(result:String,serverBean: ServerBean){
        ConnectServer.currentServer=serverBean
        setResult(1000, Intent().apply {
            putExtra("action",result)
        })
        finish()
    }

    private fun showDialog(back:()->Unit){
        AlertDialog.Builder(this).apply {
            setMessage("You are currently connected and need to disconnect before manually connecting to the server.")
            setPositiveButton("sure") { _, _ ->
                back.invoke()
            }
            setNegativeButton("cancel",null)
            show()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}