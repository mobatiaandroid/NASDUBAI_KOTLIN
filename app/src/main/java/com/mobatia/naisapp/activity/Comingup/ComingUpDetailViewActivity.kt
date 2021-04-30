package com.mobatia.naisapp.activity.Comingup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity





@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ComingUpDetailViewActivity : AppCompatActivity() {
    var url: String = ""
    var tab_type: String = ""
    lateinit var context: Context
    lateinit var titleTextView: TextView
    lateinit var logoclick: ImageView
    lateinit var back: ImageView
    lateinit var progress:ProgressBar
    lateinit var comingsoonwebview: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coming_up_detail_view)
        context = this
        InitUI()
        getSettings()
        titleTextView.text = tab_type

        back.setOnClickListener {
            finish()
        }
        logoclick.setOnClickListener {
            val mIntent = Intent(context, HomeActivity::class.java)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(mIntent)
        }
        comingsoonwebview.loadDataWithBaseURL("file:///android_asset/fonts/", url, "text/html; charset=utf-8", "utf-8", "about:blank")


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun getSettings() {
        comingsoonwebview.settings.javaScriptEnabled = true
        comingsoonwebview.settings.setSupportZoom(false)
        comingsoonwebview.settings.setAppCacheEnabled(false)
        comingsoonwebview.settings.javaScriptCanOpenWindowsAutomatically = true
        comingsoonwebview.settings.domStorageEnabled = true
        comingsoonwebview.settings.databaseEnabled = true
        comingsoonwebview.settings.defaultTextEncodingName = "utf-8"
        comingsoonwebview.settings.loadsImagesAutomatically = true
        comingsoonwebview.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        comingsoonwebview.settings.allowFileAccess = true
        comingsoonwebview.setBackgroundColor(Color.TRANSPARENT)
        comingsoonwebview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)


        comingsoonwebview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progress.visibility = View.VISIBLE
                if (newProgress == 100)
                {
                    progress.visibility = View.GONE


                }
            }
        }
    }

    private fun InitUI() {
        comingsoonwebview = findViewById(R.id.comingsoonwebview)
        titleTextView = findViewById(R.id.titleTextView)
        logoclick = findViewById(R.id.logoclick)
        back = findViewById(R.id.back)
        progress = findViewById(R.id.progress)
        url = intent.getStringExtra("webViewComingDetail")
        Log.e("Receivedurl==>"  ,url)
        tab_type = intent.getStringExtra("tab_type")
    }
}