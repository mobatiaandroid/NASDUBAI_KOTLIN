package com.mobatia.naisapp.constants

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.mobatia.naisapp.R

class InternetCheckClass {

    companion object {
        @SuppressLint("ServiceCast")
        @Suppress("DEPRECATION")
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

        //Email Pattern Check
        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun checkApiStatusError(statusCode : Int,context: Context)
        {
            if (statusCode==101)
            {
                showErrorAlert(context,"Some error occured","Alert")
            }
            else if (statusCode==102)

            {
                showErrorAlert(context,"Internal server error","Alert")
            }
            else if (statusCode==110)
            {

                showErrorAlert(context,"Invalid username/password","Alert")
            }
            else if (statusCode==113)
            {
                showErrorAlert(context,"Verification code not match","Alert")
            }
            else if (statusCode==114)
            {
                showErrorAlert(context,"Invalid username or password","Alert")
            }
            else if (statusCode==116)
            {
                showErrorAlert(context,"Token expired","Alert")
            }
            else if (statusCode==123)
            {
                showErrorAlert(context,"Invalid file access","Alert")
            }
            else if(statusCode==131)
            {
                showErrorAlert(context,"URL/Method Not found","Alert")
            }
            else if (statusCode==132)
            {
                showErrorAlert(context,"No records found","Alert")
            }
            else if (statusCode==133)
            {
                showErrorAlert(context,"Restricted access","Alert")
            }
            if (statusCode==104)
            {
                showErrorAlert(context,"field required","Alert")

            }
            else if (statusCode==105)
            {
                showErrorAlert(context,"Already exists","Alert")

            }
            else if (statusCode==103)
            {
                showErrorAlert(context,"Some error occur.","Alert")

            }
            else if (statusCode==106)
            {
                showErrorAlert(context, Resources.getSystem().getString(R.string.status_106),"Alert")

            } else if (statusCode==107)
            {
                showErrorAlert(context,Resources.getSystem().getString(R.string.status_107),"Alert")

            } else if (statusCode==108)
            {
                showErrorAlert(context,Resources.getSystem().getString(R.string.status_108),"Alert")

            } else if (statusCode==109)
            {
                showErrorAlert(context,Resources.getSystem().getString(R.string.status_109),"Alert")

            } else if (statusCode==111)
            {
                showErrorAlert(context,"No students Available","Alert")

            } else if (statusCode==115)
            {
                showErrorAlert(context,Resources.getSystem().getString(R.string.status_115),"Alert")

            } else if (statusCode==117)
            {
                showErrorAlert(context,Resources.getSystem().getString(R.string.status_117),"Alert")

            } else if (statusCode==120)
            {
                showErrorAlert(context,"Please enter a valid email address.","Alert")

            } else if (statusCode==121)
            {
                showErrorAlert(context,"The e-mail has already registered.","Alert")

            } else if (statusCode==122)
            {
                showErrorAlert(context,"Not a valid JSON","Alert")

            }
            else if (statusCode==126)
            {
                showErrorAlert(context,Resources.getSystem().getString(R.string.status_126),"Alert")

            }
        }

        fun showErrorAlert(context: Context,message : String,msgHead : String)
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
            text_dialog.text = message
            alertHead.text = msgHead
            btn_Ok.setOnClickListener()
            {
                dialog.dismiss()
            }
            dialog.show()
        }

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

        fun showSuccessAlert(context: Context,message : String,msgHead : String)
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
            text_dialog.text = message
            alertHead.text = msgHead
            iconImageView.setImageResource(R.drawable.exclamationicon)
            btn_Ok?.setOnClickListener()
            {
                dialog.dismiss()

            }
            dialog.show()
        }
    }


}
