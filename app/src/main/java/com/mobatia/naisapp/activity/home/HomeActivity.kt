package com.mobatia.naisapp.activity.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobatia.naisapp.R

class HomeActivity :AppCompatActivity()
{
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this


    }
}