package com.demo.goriddles.ac

import android.animation.ValueAnimator
import android.content.Intent
import android.view.KeyEvent
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import com.blankj.utilcode.util.ActivityUtils
import com.demo.goriddles.R
import com.demo.goriddles.admob.LoadAdImpl
import com.demo.goriddles.admob.ShowFullAd
import com.demo.goriddles.base.BaseAc
import com.demo.goriddles.conf.Local
import com.demo.goriddles.util.AdShowed
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseAc(R.layout.activity_main) {
    private var valueAnimator: ValueAnimator?=null
    private val showFullAd by lazy { ShowFullAd(this,Local.OPEN) }

    override fun initView() {
        AdShowed.readLocalNum()
        AdShowed.reset()
        LoadAdImpl.preLoadAd()
        startAnimator()
    }

    private fun startAnimator(){
        valueAnimator=ValueAnimator.ofInt(0, 100).apply {
            duration=10000L
            interpolator = LinearInterpolator()
            addUpdateListener {
                val progress = it.animatedValue as Int
                launch_progress.progress=progress
                val pro = (10 * (progress / 100.0F)).toInt()
                if (pro in 2..9){
                    showFullAd.show(
                        showing = {
                            stopAnimator()
                            launch_progress.progress=100
                        },
                        close = {
                            toHome()
                        }
                    )
                }else if (pro>=10){
                    toHome()
                }
            }
            start()
        }
    }

    private fun toHome(){
        if(!ActivityUtils.isActivityExistsInStack(HomeAc::class.java)){
            startActivity(Intent(this,HomeAc::class.java))
        }
        finish()
    }

    private fun stopAnimator(){
        valueAnimator?.removeAllUpdateListeners()
        valueAnimator?.cancel()
        valueAnimator=null
    }

    override fun onResume() {
        super.onResume()
        if (valueAnimator?.isPaused==true){
            valueAnimator?.resume()
        }
    }

    override fun onPause() {
        super.onPause()
        valueAnimator?.pause()

    }

    override fun onDestroy() {
        super.onDestroy()
        stopAnimator()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode== KeyEvent.KEYCODE_BACK){
            return true
        }
        return false
    }
}