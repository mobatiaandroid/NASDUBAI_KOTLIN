package com.mobatia.naisapp.fragment.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.constants.AppController
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PreferenceManager
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
        bannerarray = ArrayList()
        mContext = requireContext()
        homeActivity = activity as HomeActivity
        appController = AppController()
        CLICKED = homeActivity.sPosition.toString()
        listitems = resources.getStringArray(R.array.navigation_item_reg)
        mListImgArrays = context!!.resources.obtainTypedArray(R.array.navigation_item_reg_icons)
        initializeUI()
        var internetCheck = CommonMethods.isInternetAvailable(mContext)
        setListeners()
        setdraglisteners()
        getButtonBgAndTextImages()

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


    }

    private fun getButtonBgAndTextImages() {

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

        }

        private fun setBackgroundColorForView(s: String, v: View) {

        }

        private fun saveButtonBgColor(v: View, color: Int) {


        }

        private fun getTabId(textdata: String) {


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
        pager = view!!.findViewById<ViewPager>(R.id.bannerImagePager)
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

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)
            {

            }

            override fun onPageSelected(position: Int) {
                currentPage = position
            }

        })

    }




    private fun CHECKINTENTVALUE(intentTabId: String) {
        TAB_ID = intentTabId
        var mFragment: Fragment? = null

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
