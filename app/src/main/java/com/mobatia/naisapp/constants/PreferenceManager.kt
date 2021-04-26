package com.mobatia.naisapp.constants

import android.content.Context

class PreferenceManager {

    companion object{

        private val PREFSNAME = "NAS"

        /*SET USER CODE*/
        fun setUserCode(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("user_code", id)
            editor.apply()
        }

        /*GET USER CODE*/
        fun getUserCode(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("user_code", "")
        }
    }
}