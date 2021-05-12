package com.mobatia.naisapp.fragment.about_us

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.activity.staff_departments.StaffListActivity
import com.mobatia.naisapp.constants.CustomwebviewMaps
import com.mobatia.naisapp.constants.PdfReaderActivity
import com.mobatia.naisapp.constants.WebviewLoader
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.about_us.adapter.FacilityAdapter
import com.mobatia.naisapp.fragment.about_us.model.Item
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FacilityActivity : AppCompatActivity() {
    lateinit var logoclick: ImageView
    lateinit var progress: ProgressBar
    lateinit var titleTextView: TextView
    lateinit var bannerImg: ImageView
    lateinit var mail_icon: ImageView
    lateinit var description: TextView
    lateinit var facilitiesrecycler: RecyclerView
    lateinit var context: Context
    lateinit var back: ImageView
    var desc: String? = ""
    var title: String? = ""
    var image: String = ""
    lateinit var linearLayoutManager: LinearLayoutManager
    private var mAboutUsListArray: ArrayList<Item>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facility)
        InitUI()



        logoclick.setOnClickListener {
            val mIntent = Intent(context, HomeActivity::class.java)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(mIntent)
        }
        back.setOnClickListener {
            finish()
        }

        facilitiesrecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                if (mAboutUsListArray!!.size <= 1) {
                    if (mAboutUsListArray!![position].url.endsWith(".pdf")) {
                        val intent = Intent(context, PdfReaderActivity::class.java)
                        intent.putExtra("pdf_url", "Facilities")
                        intent.putExtra("pdf_title", "Facilities")
                        startActivity(intent)
                    } else {
                        val intent = Intent(context, CustomwebviewMaps::class.java)
                        intent.putExtra("webview_url", mAboutUsListArray!!.get(position).url)
                        intent.putExtra("title", "Facilities")
                        startActivity(intent)
                    }

                } else {
                    if (mAboutUsListArray!![position].url.endsWith(".pdf")) {
                        val intent = Intent(context, PdfReaderActivity::class.java)
                        intent.putExtra("pdf_url", mAboutUsListArray!!.get(position).url)
                        intent.putExtra("pdf_title", "Facilities")
                        startActivity(intent)
                    } else {
                        val intent = Intent(context, CustomwebviewMaps::class.java)
                        intent.putExtra("webview_url", mAboutUsListArray!![position].url)
                        intent.putExtra("title", "Facilities")
                        startActivity(intent)
                    }
                }

            }

        })
    }


    private fun InitUI() {
        context = this
        linearLayoutManager = LinearLayoutManager(context)
        desc = intent.getStringExtra("desc")
        title = intent.getStringExtra("title")
        image = intent.getStringExtra("image")
        mAboutUsListArray = intent.getSerializableExtra("array") as ArrayList<Item>?
        Log.e("RECEIVEDLIST==>", mAboutUsListArray.toString())
        logoclick = findViewById(R.id.logoclick)
        progress = findViewById(R.id.progress)
        titleTextView = findViewById(R.id.titleTextView)
        bannerImg = findViewById(R.id.bannerImg)
        mail_icon = findViewById(R.id.mail_icon)
        description = findViewById(R.id.description)
        facilitiesrecycler = findViewById(R.id.facilitiesrecycler)
        facilitiesrecycler.layoutManager = linearLayoutManager
        back = findViewById(R.id.back)

        titleTextView.text = title

        val mFacilityRecyclerAdapter =
            mAboutUsListArray?.let { FacilityAdapter(it) }
        facilitiesrecycler.adapter = mFacilityRecyclerAdapter


        if (desc.equals("null")) {
            description.visibility = View.GONE
        } else {
            description.visibility = View.VISIBLE
            description.text = desc

        }
//        if (banner_img.equals("null")){
//            bannerImg.setBackgroundResource(R.drawable.default_banner)
//
//        }
//        else{
//            Glide.with(context as FacilityActivity)
//                .load(banner_img)
//                .into(bannerImg)
//        }

        if (image.isNotEmpty()) {
            context.let {
                Glide.with(it)
                    .load(image)
                    .into(bannerImg)
            }
        } else {
            bannerImg.setBackgroundResource(R.drawable.default_banner)

        }

    }

}