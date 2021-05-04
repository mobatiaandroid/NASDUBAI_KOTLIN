package com.mobatia.naisapp.activity.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.adapter.HomeListAdapter
import com.mobatia.naisapp.constants.AppController
import com.mobatia.naisapp.constants.MyDragShadowBuilder
import com.mobatia.naisapp.constants.PreferenceManager
import com.mobatia.naisapp.fragment.early_years.EarlyyearsFragment
import com.mobatia.naisapp.fragment.home.HomeScreenFragment
import com.mobatia.naisapp.fragment.ibprogramme.IBProgrammeFragment
import com.mobatia.naisapp.fragment.nae_programme.NaeProgrammeFragment
import com.mobatia.naisapp.fragment.performing_arts.PerformingArtsFragment
import com.mobatia.naisapp.fragment.primary.PrimaryFragment
import com.mobatia.naisapp.fragment.reports.ReportFragment
import com.mobatia.naisapp.fragment.secondary.SecondaryFragment
import com.mobatia.naisapp.fragment.settings.SettingsFragment
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), AdapterView.OnItemLongClickListener {

    val manager = supportFragmentManager
    lateinit var navigation_menu: ImageView
    lateinit var settings_icon: ImageView
    lateinit var shadowBuilder: MyDragShadowBuilder
    lateinit var context: Context
    lateinit var clipData: ClipData
    lateinit var mListItemArray: Array<String>
    var mListImgArray: TypedArray? = null
    lateinit var linear_layout: LinearLayout
    lateinit var drawer_layout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var logoClickImgView: ImageView
    lateinit var homelist: ListView
    var mFragment: Fragment? = null
    var sPosition: Int = 0
    var previousTriggerTypeNew: Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.drawer_layout)
        Intent.FLAG_ACTIVITY_CLEAR_TASK
        initializeUI()
        showfragmenthome()
    }

    fun showfragmenthome() {
        val transaction = manager.beginTransaction()
        val fragment = HomeScreenFragment()
        transaction.replace(R.id.frame_container, fragment)
        transaction.commit()
    }

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initializeUI() {

        context = this
        homelist = findViewById<ListView>(R.id.homelistview)
        drawer_layout = findViewById(R.id.drawer_layout)
        linear_layout = findViewById(R.id.linear_layout)
        var downarrow = findViewById<ImageView>(R.id.downarrow)

        mListItemArray =
            applicationContext.resources.getStringArray(R.array.navigation_item_reg)
        mListImgArray =
            applicationContext.resources.obtainTypedArray(R.array.navigation_item_reg_icons)


        val width = (resources.displayMetrics.widthPixels / 1.7).toInt()
        val params = linear_layout
            .layoutParams as DrawerLayout.LayoutParams
        params.width = width
        linear_layout.layoutParams = params
        val myListAdapter = HomeListAdapter(this, mListItemArray, mListImgArray!!)
        homelist.adapter = myListAdapter
        homelist.onItemLongClickListener = this


        homelist.setOnItemClickListener { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            if (PreferenceManager.getUserCode(context).equals("")) {
                if (position == 0)
                {
                    mFragment = HomeScreenFragment()
                    replaceFragmentsSelected(position)
                }
                else  if (position == 8){
                    mFragment = EarlyyearsFragment()
                    replaceFragmentsSelected(position)

                }
                else  if (position == 9){
                    mFragment = PrimaryFragment()
                    replaceFragmentsSelected(position)

                }
                else  if (position == 10){
                    mFragment = SecondaryFragment()
                    replaceFragmentsSelected(position)

                }
                else  if (position == 11){
                    mFragment = IBProgrammeFragment()
                    replaceFragmentsSelected(position)

                }
                else  if (position == 14){
                    mFragment = PerformingArtsFragment()
                    replaceFragmentsSelected(position)

                }
                else  if (position == 15){
                    mFragment = ReportFragment()
                    replaceFragmentsSelected(position)

                }
                else  if (position == 18){
                    mFragment = NaeProgrammeFragment()
                    replaceFragmentsSelected(position)

                }
            }

        }

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_titlebar)
        supportActionBar?.elevation = 0F

        var view = supportActionBar?.customView
        toolbar = view!!.parent as Toolbar
        toolbar.setBackgroundColor(resources.getColor(R.color.white))
        toolbar.setContentInsetsAbsolute(0, 0)

        navigation_menu = view.findViewById(R.id.action_bar_back)
        settings_icon = view.findViewById(R.id.action_bar_forward)
        logoClickImgView = view.findViewById(R.id.logoClickImgView)
        settings_icon.visibility = View.VISIBLE
        homelist.setBackgroundColor(getColor(R.color.split_bg))
        homelist.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {}
            override fun onScroll(
                view: AbsListView,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if (view.id == homelist.id) {
                    val currentFirstVisibleItem: Int = homelist.lastVisiblePosition

                    if (currentFirstVisibleItem == totalItemCount - 1) {
                        downarrow.visibility = View.INVISIBLE
                    } else {
                        downarrow.visibility = View.VISIBLE
                    }
                }
            }
        })
        mListItemArray = context.resources.getStringArray(R.array.navigation_item_reg)
        mListImgArray = context.resources.obtainTypedArray(R.array.navigation_item_reg)
        navigation_menu.setOnClickListener {
            if (drawer_layout.isDrawerOpen(linear_layout)) {
                drawer_layout.closeDrawer(linear_layout)
            } else {
                drawer_layout.openDrawer(linear_layout)
            }
        }

        logoClickImgView.setOnClickListener(View.OnClickListener {
            settings_icon.visibility = View.VISIBLE
            if (drawer_layout.isDrawerOpen(linear_layout)) {
                drawer_layout.closeDrawer(linear_layout)
            }
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            mFragment = HomeScreenFragment()
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        })

        settings_icon.setOnClickListener {
            val fm = supportFragmentManager
            val currentFragment =
                fm.findFragmentById(R.id.frame_container)
            if (drawer_layout.isDrawerOpen(linear_layout)) {
                drawer_layout.closeDrawer(linear_layout)
            }
            mFragment = SettingsFragment()
            if (mFragment != null) {
                val fragmentManager =
                    supportFragmentManager
                fragmentManager.beginTransaction()
                    .add(R.id.frame_container, mFragment!!, "Settings")
                    .addToBackStack("Settings").commit()

                supportActionBar?.setTitle(R.string.null_value)
                settings_icon.visibility = View.INVISIBLE

            }
        }

    }


    override fun onItemLongClick(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ): Boolean {

        shadowBuilder = MyDragShadowBuilder(view)
        sPosition = position
        val selecteditem = parent?.getItemIdAtPosition(position)
        view?.setBackgroundColor(Color.parseColor("#47C2D1"))
        val data = ClipData.newPlainText("", "")
        view?.startDrag(data, shadowBuilder, view, 0)
        view!!.visibility = View.VISIBLE
        drawer_layout.closeDrawer(linear_layout)
        return false
    }

    private fun replaceFragmentsSelected(position: Int) {
        settings_icon.visibility = View.VISIBLE
        if (mFragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager
                .beginTransaction()
                .replace(
                    R.id.frame_container, mFragment!!,
                    mListItemArray[position]
                )
                .addToBackStack(mListItemArray[position]).commitAllowingStateLoss()
            homelist.setItemChecked(position, true)
            homelist.setSelection(position)
            supportActionBar?.setTitle(R.string.null_value)
            if (drawer_layout.isDrawerOpen(linear_layout)) {
                drawer_layout.closeDrawer(linear_layout)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(linear_layout)) {
            drawer_layout.closeDrawer(linear_layout)
        }
        settings_icon.visibility = View.VISIBLE

    }

    fun fragmentIntent(mFragment: Fragment?) {
        if (mFragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .add(R.id.frame_container, mFragment, AppController.mTitles)
                .addToBackStack(AppController.mTitles).commitAllowingStateLoss() //commit
        }
    }


    private fun checkpermission() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CALL_PHONE
                ),
                123
            )
        }
    }


    override fun onResume() {
        super.onResume()
        Intent.FLAG_ACTIVITY_CLEAR_TASK

    }


}
