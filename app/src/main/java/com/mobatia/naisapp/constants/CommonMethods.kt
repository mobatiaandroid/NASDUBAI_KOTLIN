package com.mobatia.naisapp.constants

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.Button
import com.mobatia.naisapp.R

class CommonMethods {
    companion object{

        //Alert with ok
        fun dialogueWithOk(activity: Activity,title:String,description:String,iconBg:Int)
        {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialogue_ok_cancel_layout)
            dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
            val btn_Ok=dialog.findViewById<Button>(R.id.btn_Ok)
            btn_Ok.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })
            dialog.show()

        }

        // Alert with ok cancel
        fun dialogueWithOkCancel(activity: Activity,title:String,description:String,iconBg:Int,action:String)
        {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialogue_ok_cancel_layout)
            dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
            val btn_Cancel=dialog.findViewById<Button>(R.id.btn_Cancel)
            btn_Cancel.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })
            val btn_Ok=dialog.findViewById<Button>(R.id.btn_Ok)
            btn_Ok.setOnClickListener(View.OnClickListener {

                if (action.equals("logout"))
                {
                    //callLogoutApi()

                }
                else if (action.equals("exit"))
                {

                }
            })
            dialog.show()

        }

        // Alert with submit cancel

        fun dialogueWithSubmitCancel(activity: Activity,title:String,description:String,iconBg:Int,action:String)
        {
            //iconImageView,titleTxt,descriptionTxt,btn_Cancel,btn_Ok
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialogue_submit_cancel_layout)
            dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
            val btn_Cancel=dialog.findViewById<Button>(R.id.btn_Cancel)
            btn_Cancel.setOnClickListener(View.OnClickListener {
                dialog.dismiss()
            })
            val btn_Ok=dialog.findViewById<Button>(R.id.btn_Ok)
            btn_Ok.setOnClickListener(View.OnClickListener {


            })
            dialog.show()

        }

        //Alert forgot password

        fun dialogueForgotPassword()
        {


        }

        // Alert signup

        fun dialogueSignup()
        {


        }

        fun isInternetAvailable(context: Context): Boolean
        {
            var result = false
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm?.run {
                    cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                        result = when {
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                            else -> false
                        }
                    }
                }
            } else {
                cm?.run {
                    cm.activeNetworkInfo?.run {
                        if (type == ConnectivityManager.TYPE_WIFI) {
                            result = true
                        } else if (type == ConnectivityManager.TYPE_MOBILE) {
                            result = true
                        }
                    }
                }
            }
            return result
        }

    }
}