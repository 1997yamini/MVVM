package com.app.demomvvm.ui.main.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.app.demomvvm.R
import com.app.demomvvm.databinding.ActivitySplashBinding
import com.app.demomvvm.ui.base.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity :  BaseActivity<ActivitySplashBinding>() {

    private fun doNext(){
        lifecycleScope.launch{
            delay(2000)
            val inn=Intent(this@SplashActivity, ContactUsActivity::class.java)
            startActivity(inn)
        }
    }

    override fun initView() {
        doNext()
    }

    override fun setListener() {
    }

    override fun getLayout(): Int =R.layout.activity_splash
}