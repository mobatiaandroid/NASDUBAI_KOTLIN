package com.mobatia.naisapp.constants

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.mobatia.naisapp.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class CommonMethods {
    companion object{

        //Alert with ok
        fun dialogueWithOk(context: Context,title:String,description:String)
        {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialogue_ok_layout)
            dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
            val btn_Ok=dialog.findViewById<Button>(R.id.btn_Ok)
            val descriptionTxt=dialog.findViewById<TextView>(R.id.descriptionTxt)
            val titleTxt=dialog.findViewById<TextView>(R.id.titleTxt)
            titleTxt.text=description
            descriptionTxt.text=title
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
            btn_Ok.setOnClickListener {


            }
            dialog.show()

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
        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        @SuppressLint("SetTextI18n")
        fun showSuccessInternetAlert(context: Context)
        {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.alert_dialogue_ok_layout)
            var iconImageView = dialog.findViewById(R.id.iconImageView) as ImageView
            var alertHead = dialog.findViewById(R.id.alertHead) as TextView
            var text_dialog = dialog.findViewById(R.id.text_dialog) as TextView
            var btn_Ok = dialog.findViewById(R.id.btn_Ok) as Button
            text_dialog.text = "Network error occurred. Please check your internet connection and try again later"
            alertHead.text = "Alert"
            iconImageView.setBackgroundResource(R.drawable.roundred)
            iconImageView.setImageResource(R.drawable.nonetworkicon)
            btn_Ok?.setOnClickListener()
            {
                dialog.dismiss()

            }
            dialog.show()
        }

        fun showErrorAlert(context: Context,message : String,msgHead : String)
        {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.alert_dialogue_ok_layout)
            var iconImageView = dialog.findViewById(R.id.iconImageView) as? ImageView
            var alertHead = dialog.findViewById(R.id.alertHead) as? TextView
            var text_dialog = dialog.findViewById(R.id.text_dialog) as? TextView
            var btn_Ok = dialog.findViewById(R.id.btn_Ok) as Button
            text_dialog?.text = message
            alertHead?.text = msgHead
            btn_Ok.setOnClickListener()
            {
                dialog.dismiss()
            }
            dialog.show()
        }

        //No DATA ALERT!!!
        fun NodataAlert(context: Context,message : String,msgHead : String)
        {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.alert_dialogue_ok_layout)
            var iconImageView = dialog.findViewById(R.id.iconImageView) as? ImageView
            var alertHead = dialog.findViewById(R.id.alertHead) as? TextView
            var text_dialog = dialog.findViewById(R.id.text_dialog) as? TextView
            var btn_Ok = dialog.findViewById(R.id.btn_Ok) as Button
            text_dialog?.text = message
            alertHead?.text = msgHead
            btn_Ok.setOnClickListener()
            {
                dialog.dismiss()
            }
            dialog.show()
        }
        // Converting date to dd-MMM-yy format

        fun dateParsingTo_dd_MMM_yyyy(date: String?): String? {
            var strCurrentDate = ""
            var format =
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            var newDate: Date? = null
            try {
                newDate = format.parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            format = SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH)
            strCurrentDate = format.format(newDate)
            return strCurrentDate
        }

        //EMAIL POPUP !!!
        fun emailalert(context: Context,message : String,email : String,title : String)
        {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.email_popup)
            val submit = dialog.findViewById(R.id.submitButton) as Button
            val cancelButton = dialog.findViewById(R.id.cancelButton) as Button

            submit.setOnClickListener()
            {
                dialog.dismiss()
            }
            cancelButton.setOnClickListener()
            {
                dialog.dismiss()
            }
            dialog.show()
        }

    }


}