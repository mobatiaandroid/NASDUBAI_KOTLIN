package com.mobatia.naisapp.activity.common

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.constants.InternetCheckClass

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mContext: Context
    lateinit var dialog: Dialog
    lateinit var forgotPasswordImg: ImageView
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

    private fun InitUI() {
        forgotPasswordImg = findViewById(R.id.forgotPasswordImg)
        guestImg = findViewById(R.id.guestImg)
        signupImg = findViewById(R.id.signupImg)
        emailTxt = findViewById(R.id.emailTxt)
        passwordEditTxt = findViewById(R.id.passwordEditTxt)
        forgotPasswordImg.setOnClickListener(this)
        signupImg.setOnClickListener(this)
        guestImg.setOnClickListener(View.OnClickListener {
            startActivity(Intent(mContext,HomeActivity::class.java))
        })

    }

    //Signup popup
    @SuppressLint("ClickableViewAccessibility")
    fun SignupDialog(context: Context) {
        dialog = Dialog(mContext, R.style.NewDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_forgot_password)
        var text_dialog = dialog.findViewById(R.id.text_dialog) as EditText
        var btn_maybelater = dialog.findViewById(R.id.btn_maybelater) as Button
        var btn_signup = dialog.findViewById(R.id.btn_signup) as Button
        btn_maybelater.text = "Maybe later";
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
                InternetCheckClass.showSuccessAlert(mContext, "Please enter Email.", "Alert")
            } else {

                val emailPattern =
                    InternetCheckClass.isEmailValid(text_dialog.text.toString().trim())
                if (!emailPattern) {
                    InternetCheckClass.showSuccessAlert(
                        mContext,
                        "Please enter a valid Email.",
                        "Alert"
                    )
                } else {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(text_dialog.windowToken, 0)
                    val internetCheck = InternetCheckClass.isInternetAvailable(mContext)
                    if (internetCheck) {
                       // callSignUpApi(text_dialog.text.toString().trim(),dialog,signup_progress)
                    } else {
                        InternetCheckClass.showSuccessInternetAlert(mContext)
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
                InternetCheckClass.showSuccessAlert(mContext, "Please enter Email.", "Alert")
            } else {
                val emailPattern =
                    InternetCheckClass.isEmailValid(text_dialog.text.toString().trim())
                if (!emailPattern) {
                    InternetCheckClass.showSuccessAlert(
                        mContext,
                        "Please enter a valid Email.",
                        "Alert"
                    )
                } else {
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    imm?.hideSoftInputFromWindow(text_dialog.windowToken, 0)
                    var internetCheck = InternetCheckClass.isInternetAvailable(mContext)
                    if (internetCheck) {
                        // callForgetPassword(text_dialog.text.toString().trim(),dialog,forgot_progress)
                    } else {
                        InternetCheckClass.showSuccessInternetAlert(mContext)
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
           // SignupDialog(mContext)
            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)

        }

    }
}