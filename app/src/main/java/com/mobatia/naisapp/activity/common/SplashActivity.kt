package com.mobatia.naisapp.activity.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mobatia.naisapp.R
import com.mobatia.naisapp.constants.PreferenceManager

class SplashActivity :AppCompatActivity() {

    lateinit var mContext:Context
    val SPLASH_TIME_OUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mContext=this
        Handler().postDelayed({
            if (PreferenceManager.getUserCode(mContext).equals(""))
            {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else
            {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }, SPLASH_TIME_OUT)
    }
}