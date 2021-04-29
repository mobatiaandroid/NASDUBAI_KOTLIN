package com.mobatia.naisapp.constants

import android.content.Context
import com.mobatia.naisapp.R

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
            return prefs.getString("button_two_reg_tab_id", "2")
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
            return prefs.getString("button_two_reg_text_image", "2")
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
            return prefs.getString("button_two_guest_tab_id", "2")
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
            return prefs.getString("button_two_guest_text_image", "2")
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
            return prefs.getString("button_three_reg_tab_id", "3")
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
            return prefs.getString("button_three_reg_text_image", "3")
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
            return prefs.getString("button_three_guest_tab_id", "3")
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
            return prefs.getString("button_three_guest_text_image", "3")
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
            return prefs.getString("button_four_reg_tab_id", "4")
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
            return prefs.getString("button_four_reg_text_image", "4")
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
            return prefs.getString("button_four_guest_tab_id", "4")
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
            return prefs.getString("button_four_guest_text_image", "4")
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
            return prefs.getString("button_five_reg_tab_id", "5")
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
            return prefs.getString("button_five_reg_text_image", "5")
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
            return prefs.getString("button_five_guest_tab_id", "5")
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
            return prefs.getString("button_five_guest_text_image", "5")
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
            return prefs.getString("button_six_reg_tab_id", "6")
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
            return prefs.getString("button_six_reg_text_image", "6")
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
            return prefs.getString("button_six_guest_tab_id", "6")
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
            return prefs.getString("button_six_guest_text_image", "6")
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
            return prefs.getString("button_seven_reg_tab_id", "7")
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
            return prefs.getString("button_seven_reg_text_image", "7")
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
            return prefs.getString("button_seven_guest_tab_id", "7")
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
            return prefs.getString("button_seven_guest_text_image", "7")
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
            return prefs.getString("button_three_reg_tab_id", "8")
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
            return prefs.getString("button_eight_reg_text_image", "8")
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
            return prefs.getString("button_eight_guest_tab_id", "8")
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
            return prefs.getString("button_eight_guest_text_image", "8")
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
            return prefs.getString("button_nine_reg_tab_id", "9")
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
            return prefs.getString("button_nine_reg_text_image", "9")
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
            return prefs.getString("button_nine_guest_tab_id", "9")
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
            return prefs.getString("button_nine_guest_text_image", "9")
        }

        fun setButtonOneGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttononeguestbg", color)
            editor.apply()
        }

        fun getButtonOneGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttononeguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtontwoGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttontwoguestbg", color)
            editor.apply()
        }

        fun getButtontwoGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttontwoguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtonthreeGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttonthreeguestbg", color)
            editor.apply()
        }

        fun getButtonthreeGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttonthreeguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtonfourGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttonfourguestbg", color)
            editor.apply()
        }

        fun getButtonfourGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttonfourguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtonfiveGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttonfiveguestbg", color)
            editor.apply()
        }

        fun getButtonfiveGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttonfiveguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtonsixGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttonsixguestbg", color)
            editor.apply()
        }

        fun getButtonsixGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttonsixguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtonsevenGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttonsevenguestbg", color)
            editor.apply()
        }

        fun getButtonsevenGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttonsevenguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtoneightGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttoneightguestbg", color)
            editor.apply()
        }

        fun getButtoneightGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttoneightguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

        fun setButtonnineGuestBg(context: Context, color: Int) {
            val prefs = context.getSharedPreferences(
                PREFSNAME, Context.MODE_PRIVATE
            )
            val editor = prefs.edit()
            editor.putInt("buttonnineguestbg", color)
            editor.apply()
        }

        fun getButtonnineGuestBg(context: Context): Int {
            val prefs = context.getSharedPreferences(
                PREFSNAME,
                Context.MODE_PRIVATE
            )
            return prefs.getInt(
                "buttonnineguestbg", context.resources
                    .getColor(R.color.transparent)
            )
        }

    }
}