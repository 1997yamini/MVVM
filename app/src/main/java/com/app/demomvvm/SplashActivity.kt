package com.app.demomvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        doNext()

    }

    private fun doNext(){
        lifecycleScope.launch{
            delay(2000)
            val inn=Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(inn)
        }
    }
}