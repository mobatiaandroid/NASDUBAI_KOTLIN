package com.mobatia.naisapp.activity.common

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Api
import com.google.firebase.iid.FirebaseInstanceId
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.common.model.LoginResponse
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.JsonConstants
import com.mobatia.naisapp.constants.PreferenceManager
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LoginActivity : AppCompatActivity(), View.OnClickListener, View.OnTouchListener {

    lateinit var mContext: Context
    lateinit var dialog: Dialog
    lateinit var forgotPasswordImg: ImageView
    lateinit var emailHelpImg: ImageView
    lateinit var loginImg: ImageView
    lateinit var signupImg: ImageView
    lateinit var guestImg: ImageView
    lateinit var emailTxt: EditText
    lateinit var passwordEditTxt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mContext = this
        InitUI()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun InitUI() {
        forgotPasswordImg = findViewById(R.id.forgotPasswordImg)
        guestImg = findViewById(R.id.guestImg)
        signupImg = findViewById(R.id.signupImg)
        emailTxt = findViewById(R.id.emailTxt)
        loginImg = findViewById(R.id.loginImg)
        passwordEditTxt = findViewById(R.id.passwordEditTxt)
        emailHelpImg = findViewById(R.id.emailHelpImg)
        forgotPasswordImg.setOnClickListener(this)
        signupImg.setOnClickListener(this)
        emailTxt.setOnTouchListener(this)
        passwordEditTxt.setOnTouchListener(this)
        guestImg.setOnClickListener(View.OnClickListener {
            startActivity(Intent(mContext, HomeActivity::class.java))
        })
        emailHelpImg.setOnClickListener(View.OnClickListener {
            if (CommonMethods.isInternetAvailable(mContext)) {
                val deliveryAddress =
                    arrayOf("communications@nasdubai.ae")
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.putExtra(Intent.EXTRA_EMAIL, deliveryAddress)
                emailIntent.type = "text/plain"
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                val pm: PackageManager = emailHelpImg.context.packageManager
                val activityList = pm.queryIntentActivities(
                    emailIntent, 0
                )
                for (app in activityList) {
                    if (app.activityInfo.name.contains("com.google.android.gm")) {
                        val activity = app.activityInfo
                        val name = ComponentName(
                            activity.applicationInfo.packageName, activity.name
                        )
                        emailIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                        emailIntent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
                        emailIntent.component = name
                        emailHelpImg.context.startActivity(emailIntent)
                        break
                    }
                }
            } else {
                // No internet alert
            }

        })


        loginImg.setOnClickListener(View.OnClickListener {

            if (emailTxt.text.toString().trim().equals("")) {
                // enter valid email
                CommonMethods.dialogueWithOk(mContext, "Please enter Email.", "Alert")

            } else {
                if (passwordEditTxt.text.toString().trim().equals("")) {
                    //enter password
                    CommonMethods.dialogueWithOk(mContext, "Please enter Password.", "Alert")
                } else {
                    var emailPattern = CommonMethods.isEmailValid(emailTxt.text.toString().trim())
                    if (!emailPattern) {
                        //enter valid email
                        CommonMethods.dialogueWithOk(
                            mContext,
                            "Please enter a valid Email.",
                            "Alert"
                        )
                    } else {
                        if (CommonMethods.isInternetAvailable(mContext)) {
                            callLoginApi(
                                emailTxt.text.toString().trim(),
                                passwordEditTxt.text.toString().trim()
                            )
                        } else {
                            CommonMethods.showSuccessInternetAlert(mContext)
                        }
                    }
                }
            }
        })

    }

    //Signup popup
    @SuppressLint("ClickableViewAccessibility")
    fun SignupDialog(context: Context) {
        dialog = Dialog(mContext, R.style.NewDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_forgot_password)
        var text_dialog = dialog.findViewById(R.id.text_dialog) as? EditText
        var btn_maybelater = dialog.findViewById(R.id.btn_maybelater) as? Button
        var btn_signup = dialog.findViewById(R.id.btn_signup) as? Button
        btn_maybelater?.text = "Maybe later";
        text_dialog?.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                text_dialog.isFocusable = false
                text_dialog.isFocusableInTouchMode = false
                false
            } else {
                text_dialog.isFocusable = false
                text_dialog.isFocusableInTouchMode = false
                false
            }
        }

        btn_signup?.setOnClickListener()
        {
            if (text_dialog?.text.toString().trim().equals("")) {
                CommonMethods.showErrorAlert(mContext, "Please enter Email.", "Alert")
            } else {

                val emailPattern =
                    CommonMethods.isEmailValid(text_dialog?.text.toString().trim())
                if (!emailPattern) {
                    CommonMethods.showErrorAlert(
                        mContext,
                        "Please enter a valid Email.",
                        "Alert"
                    )
                } else {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(text_dialog?.windowToken, 0)
                    val internetCheck = CommonMethods.isInternetAvailable(mContext)
                    if (internetCheck) {
                        callSignUpApi(text_dialog?.text.toString().trim(), dialog)
                    } else {
                        CommonMethods.showSuccessInternetAlert(mContext)
                    }


                }
            }
        }

        text_dialog?.setOnTouchListener { v, m -> // Perform tasks here
            text_dialog.isFocusable = true
            text_dialog.isFocusableInTouchMode = true
            false
        }

        btn_maybelater?.setOnClickListener()
        {
            dialog.dismiss()
        }
        dialog.show()

    }

    fun callSignUpApi(email: String, dialog: Dialog) {
        val call: Call<ResponseBody> = ApiClient.getClient.registeruser(
            email
        )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val responsedata = response.body()
                try {
                    val jsonObject = JSONObject(responsedata?.string())
                    if (jsonObject.has("status")) {
                        val status: Int = jsonObject.optInt("status")
                        when (status) {
                            100 -> {
                                showSuccessAlertForgot(
                                    mContext,
                                    "Password successfully sent to your email. Please check.",
                                    "Success",
                                    dialog
                                )

                            }
                            103 -> {
                                showSuccessAlertForgot(mContext, "Invalid email", "Alert", dialog)

                            }
                            114 -> {
                                showSuccessAlertForgot(
                                    mContext,
                                    "User not found in our database",
                                    "Alert",
                                    dialog
                                )

                            }
                            121 -> {
                                showSuccessAlertForgot(
                                    mContext,
                                    "The e-mail has already registered",
                                    "Alert",
                                    dialog
                                )

                            }
                        }

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        })
    }


    //Forget Password popup
    @SuppressLint("ClickableViewAccessibility")
    fun showForgetPassword(context: Context) {
        dialog = Dialog(mContext, R.style.NewDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_forgot_password)
        var text_dialog = dialog.findViewById(R.id.text_dialog) as EditText
        var btn_maybelater = dialog.findViewById(R.id.btn_maybelater) as Button
        var btn_signup = dialog.findViewById(R.id.btn_signup) as Button
        text_dialog.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                text_dialog.isFocusable = false
                text_dialog.isFocusableInTouchMode = false
                false
            } else {
                text_dialog.isFocusable = false
                text_dialog.isFocusableInTouchMode = false
                false
            }
        }

        btn_signup.setOnClickListener()
        {
            if (text_dialog.text.toString().trim().equals("")) {
                CommonMethods.showErrorAlert(mContext, "Please enter Email.", "Alert")
            } else {
                val emailPattern =
                    CommonMethods.isEmailValid(text_dialog.text.toString().trim())
                if (!emailPattern) {
                    CommonMethods.showErrorAlert(
                        mContext,
                        "Please enter a valid Email.",
                        "Alert"
                    )
                } else {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(text_dialog.windowToken, 0)
                    var internetCheck = CommonMethods.isInternetAvailable(mContext)
                    if (internetCheck) {
                        callForgetPassword(text_dialog.text.toString().trim(), dialog)
                    } else {
                        CommonMethods.showSuccessInternetAlert(mContext)
                    }


                }
            }
        }

        text_dialog.setOnTouchListener { v, m -> // Perform tasks here
            text_dialog.isFocusable = true
            text_dialog.isFocusableInTouchMode = true
            false
        }

        btn_maybelater.setOnClickListener()
        {
            dialog.dismiss()
        }
        dialog.show()

    }

    fun callForgetPassword(email: String, dialog: Dialog) {

        val call: Call<ResponseBody> = ApiClient.getClient.forgotpassword(email)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val responsedata = response.body()
                if (responsedata != null) {
                    try {
                        val jsonObject = JSONObject(responsedata.string())
                        if (jsonObject.has("status")) {
                            val status: Int = jsonObject.optInt("status")
                            val message: String = jsonObject.optString("message")
                            Log.e("forgotpassstatus:", status.toString())
                            Log.e("forgotpassmessage:", message)

                            if (status == 100) {
                                showSuccessAlertForgot(
                                    mContext,
                                    "Password successfully sent to your email. Please check.",
                                    "Success",
                                    dialog
                                )
                            } else {
                                showSuccessAlertForgot(mContext, message, "Alert", dialog)

                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        })
    }

    fun showSuccessAlertForgot(
        context: Context,
        message: String,
        msgHead: String,
        dialogPassword: Dialog
    ) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alert_dialogue_ok_layout)
        var iconImageView = dialog.findViewById(R.id.iconImageView) as? ImageView
        var alertHead = dialog.findViewById(R.id.alertHead) as? TextView
        var text_dialog = dialog.findViewById(R.id.text_dialog) as? TextView
        var btn_Ok = dialog.findViewById(R.id.btn_Ok) as? Button
        text_dialog?.text = message
        Log.e("printeddata:", message)
        alertHead?.text = msgHead
        iconImageView?.setImageResource(R.drawable.exclamationicon)
        btn_Ok?.setOnClickListener()
        {
            dialogPassword.dismiss()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onClick(v: View?) {
        if (v === forgotPasswordImg) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(emailTxt.windowToken, 0)
            val immq = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            immq?.hideSoftInputFromWindow(passwordEditTxt.windowToken, 0)
            showForgetPassword(mContext)
        }
        if (v == signupImg) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(emailTxt.windowToken, 0)
            val immq = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            immq?.hideSoftInputFromWindow(passwordEditTxt.windowToken, 0)
            SignupDialog(mContext)
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)

        }

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (v) {
            emailTxt -> {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        emailTxt?.isFocusable = true
                        emailTxt?.isFocusableInTouchMode = true
                    }
                    MotionEvent.ACTION_UP -> {
                        v.performClick()
                        emailTxt?.isFocusable = true
                        emailTxt?.isFocusableInTouchMode = true
                    }
                }
            }
            passwordEditTxt -> {
                when (event!!.action) {
                    MotionEvent.ACTION_DOWN -> {
                        passwordEditTxt?.isFocusable = true
                        passwordEditTxt?.isFocusableInTouchMode = true
                    }
                    MotionEvent.ACTION_UP -> {
                        v.performClick()
                        passwordEditTxt?.isFocusable = true
                        passwordEditTxt?.isFocusableInTouchMode = true
                    }
                }
            }
        }
        return false
    }

    @SuppressLint("HardwareIds")
    fun callLoginApi(email: String, password: String) {
        var androidID = Settings.Secure.getString(
            this.contentResolver,
            Settings.Secure.ANDROID_ID
        )
           System.out.println("LOGINRESPONSE:"+"email:"+email+"pass:"+password+"devid:  "+androidID+" FCM ID : "+ FirebaseInstanceId.getInstance().token.toString())
        val call: Call<LoginResponse> = ApiClient.getClient.login(
            email, password, 2, "123456789", androidID
        )

        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Failed", t.localizedMessage)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val responsedata = response.body()
                Log.e("Response Signup", responsedata.toString())
                if (responsedata != null) {
                    try {
                        val status=responsedata.status
                        Log.e("STATUS", status.toString())
                        if (status==100)
                        {
                            PreferenceManager.setUserCode(mContext,responsedata.data.token)
                            PreferenceManager.setUserEmail(mContext,responsedata.data.user_details.email)
                            dialogueWithOk(mContext,"Successfully Logged in","Alert","success")
                        }
                        else
                        {
                            dialogueWithOk(mContext,responsedata.message,"Alert","error")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        })

    }


    fun dialogueWithOk(context: Context,title:String,description:String,action:String)
    {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialogue_ok_layout)
        dialog.getWindow()!!.setBackgroundDrawableResource(android.R.color.transparent)
        val btn_Ok=dialog.findViewById<Button>(R.id.btn_Ok)
        val descriptionTxt=dialog.findViewById<TextView>(R.id.descriptionTxt)
        val titleTxt=dialog.findViewById<TextView>(R.id.titleTxt)
        val iconImageView=dialog.findViewById<ImageView>(R.id.iconImageView)
        titleTxt.text=description
        descriptionTxt.text=title
        iconImageView.setImageResource(R.drawable.exclamationicon)
        btn_Ok.setOnClickListener(View.OnClickListener {
            if(action.equals("success"))
            {
                startActivity(Intent(context,HomeActivity::class.java))
                dialog.dismiss()
                finish()
            }
            else{
                dialog.dismiss()
            }


        })
        dialog.show()

    }
}