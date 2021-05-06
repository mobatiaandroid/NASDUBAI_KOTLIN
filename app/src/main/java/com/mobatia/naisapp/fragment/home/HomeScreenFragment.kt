package com.mobatia.naisapp.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.common.banner.adapter.CustomPagerAdapter
import com.mobatia.naisapp.activity.common.banner.model.Bannerresponse
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.constants.*
import com.mobatia.naisapp.fragment.absence.AbsenceFragment
import com.mobatia.naisapp.fragment.ibprogramme.IBProgrammeFragment
import com.mobatia.naisapp.fragment.parentsessentials.ParentsEssentialsFragment
import com.mobatia.naisapp.fragment.permissionslips.PermissionSlipsFragment
import com.mobatia.naisapp.fragment.primary.PrimaryFragment
import com.mobatia.naisapp.fragment.reports.ReportFragment
import com.mobatia.naisapp.fragment.secondary.SecondaryFragment
import kotlinx.android.synthetic.main.fragment_homescreen.*
import retrofit2.Call
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

lateinit var relone: RelativeLayout
lateinit var reltwo: RelativeLayout
lateinit var relthree: RelativeLayout
lateinit var relfour: RelativeLayout
lateinit var relfive: RelativeLayout
lateinit var relsix: RelativeLayout
lateinit var relseven: RelativeLayout
lateinit var releight: RelativeLayout
lateinit var relnine: RelativeLayout
lateinit var reltxtnine: TextView

lateinit var relTxtone: TextView
lateinit var relTxttwo: TextView
lateinit var relTxtfive: TextView
lateinit var relTxtsix: TextView
lateinit var relTxtseven: TextView
lateinit var relTxteight: TextView
lateinit var relTxtthree: TextView
lateinit var relTxtfour: TextView

lateinit var relImgone: ImageView
lateinit var relImgtwo: ImageView
lateinit var relImgthree: ImageView
lateinit var relImgfour: ImageView
lateinit var relImgfive: ImageView
lateinit var relImgsix: ImageView
lateinit var relImgseven: ImageView
lateinit var relImgeight: ImageView
lateinit var relImgnine: ImageView
var versionfromapi: String = ""
var currentversion: String = ""


lateinit var mSectionText: Array<String?>
lateinit var homeActivity: HomeActivity
lateinit var appController: AppController
lateinit var listitems: Array<String>
lateinit var mListImgArrays: TypedArray
lateinit var TouchedView: View
//lateinit var TAB_ID: String
private var TAB_ID: String = ""
private var CLICKED: String = ""
private var INTENT_TAB_ID: String = ""
private var TABIDfragment: String = ""
private var usertype: String = ""
private var USERDATA: String = ""
private var previousTriggerType: Int = 0

lateinit var pager: ViewPager
var isDraggable: Boolean = false
lateinit var mContext: Context


@Suppress(
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "CAST_NEVER_SUCCEEDS",
    "ControlFlowWithEmptyBody"
)
class HomeScreenFragment : Fragment(), View.OnClickListener {

    var currentPage: Int = 0
    var bannerarray = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_homescreen, container, false)
    }

    @SuppressLint("UseRequireInsteadOfGet", "Recycle")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireContext()
        homeActivity = activity as HomeActivity
        appController = AppController()
        CLICKED = homeActivity.sPosition.toString()
        listitems = resources.getStringArray(R.array.navigation_item_reg)
        mListImgArrays = context!!.resources.obtainTypedArray(R.array.navigation_item_reg_icons)
        initializeUI()
        getbannerimages()

        var internetCheck = CommonMethods.isInternetAvailable(mContext)
        setListeners()
        setdraglisteners()
        getButtonBgAndTextImages()

    }

    private fun getbannerimages() {
        bannerarray = ArrayList()
        val  call: Call<Bannerresponse> = ApiClient.getClient.bannerimages()
        call.enqueue(object :retrofit2.Callback<Bannerresponse>{
            override fun onFailure(call: Call<Bannerresponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Bannerresponse>,
                response: Response<Bannerresponse>
            ) {
                if (response.body()!!.status==100){
                    bannerarray.addAll(response.body()!!.data.banner_images)

                    val handler = Handler()
                    val update = Runnable {
                        if (currentPage == bannerarray.size
                        ) {
                            currentPage = 0
                            pager.setCurrentItem(
                                currentPage,
                                false
                            )
                        } else {
                            pager
                                .setCurrentItem(currentPage++, true)
                        }
                    }
                    val swipeTimer = Timer()
                    swipeTimer.schedule(object : TimerTask() {
                        override fun run() {
                            handler.post(update)
                        }
                    }, 100, 3000)

                    val pageradapter = CustomPagerAdapter(mContext,bannerarray)
                    pager.adapter = pageradapter

                }
            }

        })

    }

    private fun setListeners() {
        relone.setOnClickListener(this)
        reltwo.setOnClickListener(this)
        relthree.setOnClickListener(this)
        relfour.setOnClickListener(this)
        relfive.setOnClickListener(this)
        relsix.setOnClickListener(this)
        relseven.setOnClickListener(this)
        releight.setOnClickListener(this)
        relnine.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (v == relone) {

            INTENT_TAB_ID = PreferenceManager.getButtonOneRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == reltwo) {

            INTENT_TAB_ID = PreferenceManager.getButtonTwoRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == relthree) {

            INTENT_TAB_ID = PreferenceManager.getButtonThreeRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == relfour) {

            INTENT_TAB_ID = PreferenceManager.getButtonFourRegTabID(
                mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == relfive) {

            INTENT_TAB_ID = PreferenceManager.getButtonFiveRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == relsix) {

            INTENT_TAB_ID = PreferenceManager.getButtonSixRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == relseven) {

            INTENT_TAB_ID = PreferenceManager.getButtonSevenRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == releight) {

            INTENT_TAB_ID = PreferenceManager.getButtonEightRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
        if (v == relnine) {

            INTENT_TAB_ID = PreferenceManager.getButtonNineRegTabID(mContext).toString()
            CHECKINTENTVALUE(INTENT_TAB_ID)
        }
    }

    private fun getButtonBgAndTextImages() {
        if (PreferenceManager
                .getButtonOneRegTextImage(mContext)!!.toInt() != 0
        ) {
            relImgone.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonOneRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr =
                if (listitems[PreferenceManager
                        .getButtonOneRegTextImage(mContext)!!.toInt()].equals(
                        JsonConstants.CCAS,
                        ignoreCase = true
                    )
                ) {
                    JsonConstants.CCAS
                }
                else {
                    listitems[PreferenceManager
                        .getButtonOneRegTextImage(mContext)!!.toInt()].toUpperCase()
                }
            relTxtone.text = relTwoStr
            relTxtone.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            relone.setBackgroundColor(
                PreferenceManager
                    .getButtonOneGuestBg(mContext)
            )
        }
        if (PreferenceManager.getButtonTwoRegTextImage(mContext)!!.toInt() != 0
        ) {
            relImgtwo.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonTwoRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr =
                if (listitems[PreferenceManager
                        .getButtonTwoRegTextImage(mContext)!!.toInt()].equals(
                        JsonConstants.CCAS,
                        ignoreCase = true
                    )
                ) {
                    JsonConstants.CCAS
                } else {
                    listitems[PreferenceManager
                        .getButtonTwoRegTextImage(mContext)!!.toInt()].toUpperCase()
                }
            relTxttwo.text = relTwoStr
            relTxttwo.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            reltwo.setBackgroundColor(
                PreferenceManager
                    .getButtontwoGuestBg(mContext)
            )
        }
        if (PreferenceManager
                .getButtonThreeRegTextImage(mContext)!!.toInt() != 0
        ) {
            relImgthree.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonThreeRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr = if (listitems[PreferenceManager
                    .getButtonThreeRegTextImage(mContext)!!.toInt()].equals(
                    JsonConstants.CCAS,
                    ignoreCase = true
                )
            ) {
                JsonConstants.CCAS
            } else {
                listitems[PreferenceManager
                    .getButtonThreeRegTextImage(mContext)!!.toInt()].toUpperCase()
            }
            relTxtthree.text = relTwoStr
            relTxtthree.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            relthree.setBackgroundColor(
                PreferenceManager
                    .getButtonthreeGuestBg(mContext)
            )
        }


        if (PreferenceManager
                .getButtonFourRegTextImage(mContext)!!.toInt() != 0
        ) {
            relImgfour.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonFourRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr = if (listitems[PreferenceManager
                    .getButtonFourRegTextImage(mContext)!!.toInt()].equals(
                    JsonConstants.CCAS,
                    ignoreCase = true
                )
            ) {
                JsonConstants.CCAS
            }
          else {
                listitems[PreferenceManager
                    .getButtonFourRegTextImage(mContext)!!.toInt()].toUpperCase()
            }
            relTxtfour.text = relTwoStr
            relTxtfour.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            relfour.setBackgroundColor(
                PreferenceManager
                    .getButtonfourGuestBg(mContext)
            )
        }


        if (PreferenceManager.getButtonFiveRegTextImage(mContext)!!.toInt() != 0)
        {
            relImgfive.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonFourRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr = if (listitems[PreferenceManager
                    .getButtonFourRegTextImage(mContext)!!.toInt()].equals(
                    JsonConstants.CCAS,
                    ignoreCase = true
                )
            ) {
                JsonConstants.CCAS
            } else {
                listitems[PreferenceManager
                    .getButtonFourRegTextImage(mContext)!!.toInt()].toUpperCase()
            }
            relTxtfive.text = relTwoStr
            relTxtfive.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            relfive.setBackgroundColor(
                PreferenceManager
                    .getButtonfiveGuestBg(mContext)
            )
        }
        if (PreferenceManager.getButtonSixRegTextImage(mContext)!!.toInt() != 0) {
            relImgsix.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonSixRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr = if (listitems[PreferenceManager
                    .getButtonSixRegTextImage(mContext)!!.toInt()].equals(
                    JsonConstants.CCAS,
                    ignoreCase = true
                )
            ) {
                JsonConstants .CCAS
            }  else {
                listitems[PreferenceManager
                    .getButtonSixRegTextImage(mContext)!!.toInt()].toUpperCase(Locale.ROOT)
            }
            relTxtsix.text = relTwoStr
            relTxtsix.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            relsix.setBackgroundColor(
                PreferenceManager
                    .getButtonsixGuestBg(mContext)
            )
        }
        if (PreferenceManager
                .getButtonSevenRegTextImage(mContext)!!.toInt() != 0
        ) {
            relImgseven.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonSevenRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr = if (listitems[PreferenceManager
                    .getButtonSevenRegTextImage(mContext)!!.toInt()].equals(
                    JsonConstants.CCAS,
                    ignoreCase = true
                )
            ) {
                JsonConstants.CCAS
            } else {
                listitems[PreferenceManager
                    .getButtonSevenRegTextImage(mContext)!!.toInt()].toUpperCase()
            }
            relTxtseven.text = relTwoStr
            relTxtseven.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            relseven.setBackgroundColor(
                PreferenceManager
                    .getButtonsevenGuestBg(mContext)
            )
        }
        if (PreferenceManager
                .getButtonEightRegTextImage(mContext)!!.toInt() != 0
        ) {
            relImgeight.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonEightRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr = if (listitems[PreferenceManager
                    .getButtonEightRegTextImage(mContext)!!.toInt()].equals(
                    JsonConstants.CCAS,
                    ignoreCase = true
                )
            ) {
                JsonConstants.CCAS
            } else {
                listitems[PreferenceManager
                    .getButtonEightRegTextImage(mContext)!!.toInt()].toUpperCase()
            }
            relTxteight.text = relTwoStr
            relTxteight.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            releight.setBackgroundColor(
                PreferenceManager
                    .getButtoneightGuestBg(mContext)
            )
        }
        if (PreferenceManager
                .getButtonNineRegTextImage(mContext)!!.toInt() != 0
        ) {
            relImgnine.setImageDrawable(
                mListImgArrays.getDrawable(
                    PreferenceManager
                        .getButtonNineRegTextImage(mContext)!!.toInt()
                )
            )
            var relTwoStr: String? = ""
            relTwoStr = if (listitems[PreferenceManager
                    .getButtonNineRegTextImage(mContext)!!.toInt()].equals(
                    JsonConstants.CCAS,
                    ignoreCase = true
                )
            ) {
                JsonConstants.CCAS
            } else {
                listitems[PreferenceManager
                    .getButtonNineRegTextImage(mContext)!!.toInt()].toUpperCase(Locale.ROOT)
            }
            reltxtnine.text = relTwoStr
            reltxtnine.setTextColor(ContextCompat.getColor(mContext, R.color.white))
            relnine.setBackgroundColor(
                PreferenceManager
                    .getButtonnineGuestBg(mContext)
            )
        }

    }

    private fun setdraglisteners() {
        relone.setOnDragListener(DropListener())
        reltwo.setOnDragListener(DropListener())
        relthree.setOnDragListener(DropListener())
        relfour.setOnDragListener(DropListener())
        relfive.setOnDragListener(DropListener())
        relsix.setOnDragListener(DropListener())
        relseven.setOnDragListener(DropListener())
        releight.setOnDragListener(DropListener())
        relnine.setOnDragListener(DropListener())
    }

    @Suppress("EqualsBetweenInconvertibleTypes")
    class DropListener : View.OnDragListener {
        override fun onDrag(v: View?, event: DragEvent?): Boolean {

            when (event?.action) {
                DragEvent.ACTION_DRAG_STARTED -> Log.d("DRAG", "START")
                DragEvent.ACTION_DRAG_ENTERED -> Log.d("DRAG", "ENTERED")
                DragEvent.ACTION_DRAG_EXITED -> Log.d("DRAG", "EXITED")
                DragEvent.ACTION_DROP -> {
                    val intArray = IntArray(2)
                    v?.getLocationInWindow(intArray)
                    val x = intArray[0]
                    val y = intArray[1]
                    val sPosition = homeActivity.sPosition
                    getButtonViewTouched(x.toFloat().toInt(), y.toFloat().toInt())
                    mSectionText[0] = relTxtone.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[1] = relTxttwo.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[2] = relTxtthree.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[3] = relTxtfour.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[4] = relTxtfive.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[5] = relTxtsix.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[6] = relTxtseven.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[7] = relTxteight.text.toString().toUpperCase(Locale.ENGLISH)
                    mSectionText[8] = reltxtnine.text.toString().toUpperCase(Locale.ENGLISH)

                    for (i in mSectionText.indices) {
                        isDraggable = true
                        if (mSectionText[i].equals(
                                listitems[homeActivity.sPosition],
                                ignoreCase = true
                            )
                        ) {
                            isDraggable = false
                            break
                        }
                    }
                    if (isDraggable) {
                        getButtonDrawablesAndText(TouchedView, homeActivity.sPosition)

                    } else {

                        Toast.makeText(mContext, "Item Already Exists !!!", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                DragEvent.ACTION_DRAG_ENDED -> Log.d("DRAG", "END")


            }


            return true

        }

        private fun getButtonDrawablesAndText(touchedView: View, sPosition: Int) {
            if (sPosition != 0) {
                if (touchedView == relone) {
                    relImgone.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    } else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxtone.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonOneRegTabID(mContext, TAB_ID)
                    //setBackgroundColorForView(appController.listitemArrays[sPosition], relone)
                    setBackgroundColorForView(listitems[sPosition], relone)
                    PreferenceManager.setButtonOneRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == reltwo) {
                    relImgtwo.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    } else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxttwo.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonTwoRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], reltwo)
                    PreferenceManager.setButtonTwoRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == relthree) {
                    relImgthree.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    } else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxtthree.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonThreeRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], relthree)
                    PreferenceManager.setButtonThreeRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == relfour) {
                    relImgfour.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    } else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxtfour.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonFourRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], relfour)
                    PreferenceManager.setButtonFourRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == relfive) {
                    relImgfive.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    } else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxtfive.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonFiveRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], relfive)
                    PreferenceManager.setButtonFiveRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == relsix) {
                    relImgsix.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    }  else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxtsix.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonSixRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], relsix)
                    PreferenceManager.setButtonSixRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == relseven) {
                    relImgseven.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    } else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxtseven.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonSevenRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], relseven)
                    PreferenceManager.setButtonSevenRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == releight) {
                    relImgeight.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    }else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    relTxteight.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonEightRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], releight)
                    PreferenceManager.setButtonEightRegTextImage(mContext, sPosition.toString())
                } else if (touchedView == relnine) {
                    relImgnine.setImageDrawable(mListImgArrays.getDrawable(sPosition))
                    val relstring: String
                    if (listitems[sPosition] == "CCAs") {
                        relstring = "CCAS"
                    }
                    else {
                        relstring = listitems[sPosition].toUpperCase(Locale.getDefault())
                    }
                    reltxtnine.text = relstring
                    getTabId(listitems[sPosition])
                    PreferenceManager.setButtonNineRegTabID(mContext, TAB_ID)
                    setBackgroundColorForView(listitems[sPosition], relnine)
                    PreferenceManager.setButtonNineRegTextImage(mContext, sPosition.toString())
                }

            }
        }

        private fun setBackgroundColorForView(s: String, v: View) {
            if (v == relone) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == reltwo) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == relthree) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == relfour) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == relfive) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rel_five))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == relsix) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == relseven) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == releight) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            } else if (v == relnine) {
                v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
                saveButtonBgColor(v, ContextCompat.getColor(mContext, R.color.transparent))
            }
        }

        private fun saveButtonBgColor(v: View, color: Int) {

            if (v == relone) {
                PreferenceManager.setButtonOneGuestBg(mContext, color)
            } else if (v == reltwo) {
                PreferenceManager.setButtontwoGuestBg(mContext, color)
            } else if (v == relthree) {
                PreferenceManager.setButtonthreeGuestBg(mContext, color)
            } else if (v == relfour) {
                PreferenceManager.setButtonfourGuestBg(mContext, color)
            } else if (v == relfive) {
                PreferenceManager.setButtonfiveGuestBg(mContext, color)
            } else if (v == relsix) {
                PreferenceManager.setButtonsixGuestBg(mContext, color)
            } else if (v == relseven) {
                PreferenceManager.setButtonsevenGuestBg(mContext, color)
            } else if (v == releight) {
                PreferenceManager.setButtoneightGuestBg(mContext, color)
            } else if (v == relnine) {
                PreferenceManager.setButtonnineGuestBg(mContext, color)
            }
        }

        private fun getTabId(textdata: String) {

            when {

                textdata.equals(ClassNameConstants.CALENDAR) -> {
                    TAB_ID = NasTabConstants.TAB_CALENDAR_REG

                }
                textdata.equals(ClassNameConstants.NOTIFICATIONS) -> {
                    TAB_ID = NasTabConstants.TAB_NOTIFICATIONS_REG

                }

                textdata.equals(ClassNameConstants.COMMUNICATIONS, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_COMMUNICATIONS_REG

                }
                textdata.equals(ClassNameConstants.ABSENCES, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_ABSENCES_REG
                }
                textdata.equals(ClassNameConstants.PAYMENT, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_TRIPS_REG
                }
                textdata.equals(ClassNameConstants.CANTEEN, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_CANTEEN_REG
                }
                textdata.equals(ClassNameConstants.PARENT_ESSENTIALS, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_PARENT_ESSENTIALS_REG
                }
                textdata.equals(ClassNameConstants.EARLY_YEARS, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_EARLYYEARS_REG
                }
                textdata.equals(ClassNameConstants.PRIMARY, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_PRIMARY_REG
                }
                textdata.equals(ClassNameConstants.SECONDARY, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_SECONDARY_REG
                }

                textdata.equals(ClassNameConstants.IB_PROGRAMME, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_IB_PROGRAMME_REG

                }
                textdata.equals(ClassNameConstants.SPORTS, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_SPORTS_REG

                }
                textdata.equals(ClassNameConstants.PERFORMING_ARTS, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_PERFORMING_ARTS_REG

                }
                textdata.equals(ClassNameConstants.CCAS, ignoreCase = true) -> {
                    TAB_ID = NasTabConstants.TAB_CCAS_REG

                }
                textdata.equals(ClassNameConstants.PARENTS_ASSOCIATION, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_PARENTS_ASSOCIATION_REG
                }
               textdata.equals(ClassNameConstants.PARENT_EVENING, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_PARENTS_MEETING_REG
                }
                textdata.equals(ClassNameConstants.NAE_PROGRAMMES, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_NAE_PROGRAMMES_REG
                }
                textdata.equals(ClassNameConstants.REPORT, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_REPORT_REG
                }
                textdata.equals(ClassNameConstants.PERMISSION_FORMS, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_PERMISSION_FORM_REG
                }
                textdata.equals(ClassNameConstants.GALLERY, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_GALLERY_REG
                }
                textdata.equals(ClassNameConstants.ABOUT_US, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_ABOUT_US_REG
                }
                textdata.equals(ClassNameConstants.CONTACT_US, ignoreCase = true) -> {
                TAB_ID = NasTabConstants.TAB_CONTACT_US_REG
                }

            }

        }

        private fun getButtonViewTouched(centerX: Int, centerY: Int) {
            val array1 = IntArray(2)
            relone.getLocationInWindow(array1)
            val x1: Int = array1[0]
            val x2 = x1 + relone.width
            val y1: Int = array1[1]
            val y2 = y1 + relone.height

            val array2 = IntArray(2)
            reltwo.getLocationInWindow(array2)
            val x3: Int = array2[0]
            val x4 = x3 + reltwo.width
            val y3: Int = array2[1]
            val y4 = y3 + reltwo.height

            val array3 = IntArray(2)
            relthree.getLocationInWindow(array3)
            val x5: Int = array3[0]
            val x6 = x5 + relthree.width
            val y5: Int = array3[1]
            val y6 = y5 + relthree.height

            val array4 = IntArray(2)
            relfour.getLocationInWindow(array4)
            val x7: Int = array4[0]
            val x8 = x7 + relfour.width
            val y7: Int = array4[1]
            val y8 = y7 + relfour.height

            val array5 = IntArray(2)
            relfive.getLocationInWindow(array5)
            val x9: Int = array5[0]
            val x10 = x9 + relfive.width
            val y9: Int = array5[1]
            val y10 = y9 + relfive.height

            val array6 = IntArray(2)
            relsix.getLocationInWindow(array6)
            val x11: Int = array6[0]
            val x12 = x11 + relsix.width
            val y11: Int = array6[1]
            val y12 = y11 + relsix.height

            val array7 = IntArray(2)
            relseven.getLocationInWindow(array7)
            val x13: Int = array7[0]
            val x14 = x13 + relseven.width
            val y13: Int = array7[1]
            val y14 = y13 + relseven.height

            val array8 = IntArray(2)
            releight.getLocationInWindow(array8)
            val x15: Int = array8[0]
            val x16 = x15 + releight.width
            val y15: Int = array8[1]
            val y16 = y15 + releight.height

            val array9 = IntArray(2)
            relnine.getLocationInWindow(array9)
            val x17: Int = array9[0]
            val x18 = x17 + relnine.width
            val y17: Int = array9[1]
            val y18 = y17 + relnine.height

            if (centerX in x1..x2 && y1 <= centerY && centerY <= y2) {
                TouchedView = relone
            } else if (centerX in x3..x4 && y3 <= centerY && centerY <= y4) {
                TouchedView = reltwo
            } else if (centerX in x5..x6 && y5 <= centerY && centerY <= y6) {
                TouchedView = relthree
            } else if (centerX in x7..x8 && y7 <= centerY && centerY <= y8) {
                TouchedView = relfour
            } else if (centerX in x9..x10 && y9 <= centerY && centerY <= y10) {
                TouchedView = relfive
            } else if (centerX in x11..x12 && y11 <= centerY && centerY <= y12) {
                TouchedView = relsix
            } else if (centerX in x13..x14 && y13 <= centerY && centerY <= y14) {
                TouchedView = relseven
            } else if (centerX in x15..x16 && y15 <= centerY && centerY <= y16) {
                TouchedView = releight
            } else if (centerX in x17..x18 && y17 <= centerY && centerY <= y18) {
                TouchedView = relnine
            }

        }

    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun initializeUI() {
        pager = view!!.findViewById(R.id.bannerImagePager)
        relone = view!!.findViewById(R.id.relOne) as RelativeLayout
        reltwo = view!!.findViewById(R.id.relTwo) as RelativeLayout
        relthree = view!!.findViewById(R.id.relThree) as RelativeLayout
        relfour = view!!.findViewById(R.id.relFour) as RelativeLayout
        relfive = view!!.findViewById(R.id.relFive) as RelativeLayout
        relsix = view!!.findViewById(R.id.relSix) as RelativeLayout
        relseven = view!!.findViewById(R.id.relSeven) as RelativeLayout
        releight = view!!.findViewById(R.id.relEight) as RelativeLayout
        relnine = view!!.findViewById(R.id.relNine) as RelativeLayout

        relTxtone = view!!.findViewById(R.id.relTxtOne) as TextView
        relTxttwo = view!!.findViewById(R.id.relTxtTwo) as TextView
        relTxtthree = view!!.findViewById(R.id.relTxtThree) as TextView
        relTxtfour = view!!.findViewById(R.id.relTxtFour) as TextView
        relTxtfive = view!!.findViewById(R.id.relTxtFive) as TextView
        relTxtsix = view!!.findViewById(R.id.relTxtSix) as TextView
        relTxtseven = view!!.findViewById(R.id.relTxtSeven) as TextView
        relTxteight = view!!.findViewById(R.id.relTxtEight) as TextView
        reltxtnine = view!!.findViewById(R.id.relTxtNine) as TextView

        relImgone = view!!.findViewById(R.id.relImgOne) as ImageView
        relImgtwo = view!!.findViewById(R.id.relImgTwo) as ImageView
        relImgthree = view!!.findViewById(R.id.relImgThree) as ImageView
        relImgfour = view!!.findViewById(R.id.relImgFour) as ImageView
        relImgfive = view!!.findViewById(R.id.relImgFive) as ImageView
        relImgsix = view!!.findViewById(R.id.relImgSix) as ImageView
        relImgseven = view!!.findViewById(R.id.relImgSeven) as ImageView
        relImgeight = view!!.findViewById(R.id.relImgEight) as ImageView
        relImgnine = view!!.findViewById(R.id.relImgNine) as ImageView
        mSectionText = arrayOfNulls(9)

//
//        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrollStateChanged(state: Int) {
//
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
//            {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//                currentPage = position
//            }
//
//        })

    }




    private fun CHECKINTENTVALUE(intentTabId: String) {
        TAB_ID = intentTabId
        var mFragment: Fragment? = null
        Log.e("Click",intentTabId)

        when (intentTabId) {
            NasTabConstants.TAB_PRIMARY_REG -> {
                mFragment = PrimaryFragment()
                fragmentIntent(mFragment)
            }
            NasTabConstants.TAB_SECONDARY_REG -> {
                mFragment = SecondaryFragment()
                fragmentIntent(mFragment)
            }
            NasTabConstants.TAB_IB_PROGRAMME_REG -> {
                mFragment = IBProgrammeFragment()
                fragmentIntent(mFragment)
            }
            NasTabConstants.TAB_REPORT_REG -> {
                mFragment = ReportFragment()
                fragmentIntent(mFragment)
            }
            NasTabConstants.TAB_PARENT_ESSENTIALS_REG -> {
                mFragment = ParentsEssentialsFragment()
                fragmentIntent(mFragment)
            }
            NasTabConstants.TAB_PERMISSION_FORM_REG -> {
                mFragment = PermissionSlipsFragment()
                fragmentIntent(mFragment)
            }
            NasTabConstants.TAB_ABSENCES_REG -> {
                mFragment = AbsenceFragment()
                fragmentIntent(mFragment)
            }
        }
    }




    fun fragmentIntent(mFragment: Fragment?)
    {
        if (mFragment != null) {
            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction()
                .add(R.id.frame_container, mFragment, AppController.mTitles)
                .addToBackStack(AppController.mTitles).commitAllowingStateLoss() //commit
            //.commit()
        }
    }




}
