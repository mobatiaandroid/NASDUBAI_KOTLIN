package com.mobatia.naisapp.fragment.about_us

import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.constants.CustomwebviewMaps
import com.mobatia.naisapp.constants.PdfReaderActivity
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.about_us.adapter.AccreditionsAdapter
import com.mobatia.naisapp.fragment.about_us.adapter.FacilityAdapter
import com.mobatia.naisapp.fragment.about_us.model.Item
import java.util.ArrayList

class AccreditionsActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var titleTextView: TextView
    lateinit var bannerImg: ImageView
    lateinit var accreditionsrecycler: RecyclerView
    lateinit var logoclick: ImageView
    lateinit var back: ImageView
    lateinit var linearLayoutManager: LinearLayoutManager
    private var mAboutUsListArray: ArrayList<Item>? = null
    var title: String? = ""
    var image: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accreditions)
        InitUI()
    }

    private fun InitUI() {
        context = this
        linearLayoutManager = LinearLayoutManager(context)
        image = intent.getStringExtra("image")
        mAboutUsListArray = intent.getSerializableExtra("array") as ArrayList<Item>?
        titleTextView = findViewById(R.id.titleTextView)
        bannerImg = findViewById(R.id.bannerImg)
        accreditionsrecycler = findViewById(R.id.accreditionsrecycler)
        accreditionsrecycler.layoutManager = linearLayoutManager
        logoclick = findViewById(R.id.logoclick)
        back = findViewById(R.id.back)
        titleTextView.text = getString(R.string.Accreditions)

        val accreditionsAdapter =
            mAboutUsListArray?.let { AccreditionsAdapter(it) }
        accreditionsrecycler.adapter = accreditionsAdapter

        if (image.isNotEmpty()) {
            context.let {
                Glide.with(it)
                    .load(image)
                    .into(bannerImg)
            }
        } else {
            bannerImg.visibility = View.GONE
            //  bannerImg.setBackgroundResource(R.drawable.default_banner)

        }

        back.setOnClickListener {
            finish()

        }
        logoclick.setOnClickListener {
            val mIntent = Intent(context, HomeActivity::class.java)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(mIntent)
        }
        accreditionsrecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                if (mAboutUsListArray!![position].url.endsWith(".pdf")) {
                    val intent = Intent(context, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", mAboutUsListArray!![position].url)
                    intent.putExtra("pdf_title", "Accreditiations & Examinations")
                    startActivity(intent)
                } else {
                    val intent = Intent(context, CustomwebviewMaps::class.java)
                    intent.putExtra("webview_url", mAboutUsListArray!![position].url)
                    intent.putExtra("title", "Accreditiations & Examinations")
                    startActivity(intent)
                }
            }
        })
    }
}