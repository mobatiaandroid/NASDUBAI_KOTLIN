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

        /*SET FIRE BASE ID*/
        fun setFcmID(context: Context, id: String) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("firebase id", id)
            editor.apply()
        }

        /*GET FIREBASE ID*/
        fun getFcmID(context: Context): String {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("firebase id", "").toString()
        }


        /*Teal 1 settings for both reg and guest*/

        /*REG Teal 1*/
        fun setButtonOneRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_one_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonOneRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_one_reg_tab_id", "1")
        }
        fun setButtonOneRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_one_reg_text_image", id)
            editor.apply()
        }

        fun getButtonOneRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_one_reg_text_image", "1")
        }

        /*GUEST Teal 1*/
        fun setButtonOneGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_one_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonOneGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_one_guest_tab_id", "1")
        }
        fun setButtonOneGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_one_guest_text_image", id)
            editor.apply()
        }

        fun getButtonOneGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_one_guest_text_image", "1")
        }

        /*Teal 2 settings for both reg and guest*/

        /*REG Teal 2*/
        fun setButtonTwoRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_two_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonTwoRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_two_reg_tab_id", "1")
        }
        fun setButtonTwoRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_two_reg_text_image", id)
            editor.apply()
        }

        fun getButtonTwoRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_two_reg_text_image", "1")
        }

        /*GUEST Teal 2*/
        fun setButtonTwoGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_two_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonTwoGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_two_guest_tab_id", "1")
        }
        fun setButtonTwoGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_two_guest_text_image", id)
            editor.apply()
        }

        fun getButtonTwoGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_two_guest_text_image", "1")
        }


        /*Teal 3 settings for both reg and guest*/

        /*REG Teal 3*/
        fun setButtonThreeRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_three_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonThreeRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_three_reg_tab_id", "1")
        }
        fun setButtonThreeRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_three_reg_text_image", id)
            editor.apply()
        }

        fun getButtonThreeRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_three_reg_text_image", "1")
        }

        /*GUEST Teal 3*/
        fun setButtonThreeGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_three_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonThreeGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_three_guest_tab_id", "1")
        }
        fun setButtonThreeGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_three_guest_text_image", id)
            editor.apply()
        }

        fun getButtonThreeGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_three_guest_text_image", "1")
        }


        /*REG Teal 4*/
        fun setButtonFourRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_four_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonFourRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_four_reg_tab_id", "1")
        }
        fun setButtonFourRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_four_reg_text_image", id)
            editor.apply()
        }

        fun getButtonFourRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_four_reg_text_image", "1")
        }

        /*GUEST Teal 3*/
        fun setButtonFourGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_four_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonFourGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_four_guest_tab_id", "1")
        }
        fun setButtonFourGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_four_guest_text_image", id)
            editor.apply()
        }

        fun getButtonFourGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_four_guest_text_image", "1")
        }


        /*REG Teal 5*/
        fun setButtonFiveRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_five_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonFiveRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_five_reg_tab_id", "1")
        }
        fun setButtonFiveRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_four_reg_text_image", id)
            editor.apply()
        }

        fun getButtonFiveRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_five_reg_text_image", "1")
        }

        /*GUEST Teal 3*/
        fun setButtonFiveGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_five_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonFiveGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_five_guest_tab_id", "1")
        }
        fun setButtonFiveGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_five_guest_text_image", id)
            editor.apply()
        }

        fun getButtonFiveGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_five_guest_text_image", "1")
        }


        /*REG Teal 6*/
        fun setButtonSixRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_six_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonSixRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_six_reg_tab_id", "1")
        }
        fun setButtonSixRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_six_reg_text_image", id)
            editor.apply()
        }

        fun getButtonSixRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_six_reg_text_image", "1")
        }

        /*GUEST Teal 3*/
        fun setButtonSixGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_six_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonSixGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_six_guest_tab_id", "1")
        }
        fun setButtonSixGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_six_guest_text_image", id)
            editor.apply()
        }

        fun getButtonSixGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_six_guest_text_image", "1")
        }

        /*REG Teal 7*/
        fun setButtonSevenRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_three_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonSevenRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_seven_reg_tab_id", "1")
        }
        fun setButtonSevenRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_seven_reg_text_image", id)
            editor.apply()
        }

        fun getButtonSevenRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_seven_reg_text_image", "1")
        }

        /*GUEST Teal 3*/
        fun setButtonSevenGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_seven_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonSevenGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_seven_guest_tab_id", "1")
        }
        fun setButtonSevenGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_seven_guest_text_image", id)
            editor.apply()
        }

        fun getButtonSevenGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_seven_guest_text_image", "1")
        }


        /*REG Teal 8*/
        fun setButtonEightRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_eight_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonEightRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_three_reg_tab_id", "1")
        }
        fun setButtonEightRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_eight_reg_text_image", id)
            editor.apply()
        }

        fun getButtonEightRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_eight_reg_text_image", "1")
        }

        /*GUEST Teal 8*/
        fun setButtonEightGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_eight_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonEightGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_eight_guest_tab_id", "1")
        }
        fun setButtonEightGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_eight_guest_text_image", id)
            editor.apply()
        }

        fun getButtonEightGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_eight_guest_text_image", "1")
        }

        /*REG Teal 9*/
        fun setButtonNineRegTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_nine_reg_tab_id", id)
            editor.apply()
        }

        fun getButtonNineRegTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_nine_reg_tab_id", "1")
        }
        fun setButtonNineRegTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_nine_reg_text_image", id)
            editor.apply()
        }

        fun getButtonNineRegTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_nine_reg_text_image", "1")
        }

        /*GUEST Teal 3*/
        fun setButtonNineGuestTabID(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putString("button_nine_guest_tab_id", id)
            editor.apply()
        }

        fun getButtonNineGuestTabID(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_nine_guest_tab_id", "1")
        }
        fun setButtonNineGuestTextImage(context: Context, id: String?) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putString("button_nine_guest_text_image", id)
            editor.apply()
        }

        fun getButtonNineGuestTextImage(context: Context): String? {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getString("button_nine_guest_text_image", "1")
        }
    }
}