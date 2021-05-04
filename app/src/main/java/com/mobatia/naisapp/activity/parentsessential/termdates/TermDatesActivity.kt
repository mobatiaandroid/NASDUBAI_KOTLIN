package com.mobatia.naisapp.activity.parentsessential.termdates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.activity.parentsessential.adapter.SubmenuAdapter
import com.mobatia.naisapp.activity.parentsessential.model.CommonSubMenuModel
import com.mobatia.naisapp.activity.parentsessential.termdates.model.TermDatesResponse
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PdfReaderActivity
import com.mobatia.naisapp.constants.WebviewLoader
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.primary.adapter.PrimaryDetailsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TermDatesActivity : AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var titleTextView: TextView
    lateinit var TermDatesRecycler: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress: RelativeLayout
    lateinit var backRelative: RelativeLayout
    lateinit var bannerImagePager: ImageView
    lateinit var logoclick: ImageView
    var termDatesArrayList = ArrayList<CommonSubMenuModel>()


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_dates)
        InitUI()
        backRelative.setOnClickListener {
            finish()
        }
        logoclick.setOnClickListener {
            val mIntent = Intent(mContext, HomeActivity::class.java)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(mIntent)
        }
    }


    private fun InitUI() {
        mContext = this
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = findViewById(R.id.heading)
        backRelative = findViewById(R.id.backRelative)
        logoclick = findViewById(R.id.relative_logo_header)
        TermDatesRecycler = findViewById(R.id.mListView)
        bannerImagePager = findViewById(R.id.bannerImagePager)
        progress = findViewById(R.id.progressDialog)
        TermDatesRecycler.layoutManager = linearLayoutManager
        titleTextView.text = "Term Dates"

        if (CommonMethods.isInternetAvailable(mContext)) {
            callTermDatesDetailAPI()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }


        TermDatesRecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val urltype = termDatesArrayList[position].file
                if (urltype.contains("pdf")) {
                    val intent = Intent(mContext, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", termDatesArrayList[position].file)
                    intent.putExtra("pdf_title", termDatesArrayList[position].submenu)
                    startActivity(intent)
                } else {
                    val intent = Intent(mContext, WebviewLoader::class.java)
                    intent.putExtra("webview_url", termDatesArrayList[position].file)
                    startActivity(intent)
                }

            }

        })
    }

    private fun callTermDatesDetailAPI() {
        termDatesArrayList = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<TermDatesResponse> = ApiClient.getClient.termDates( 1)
        call.enqueue(object : Callback<TermDatesResponse> {
            override fun onFailure(call: Call<TermDatesResponse>, t: Throwable) {
                progress.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<TermDatesResponse>,
                response: Response<TermDatesResponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                  val bannerImg:String=response.body()!!.data.banner_image
                    termDatesArrayList.addAll(response.body()!!.data.termDatesArrayList)
                    if (bannerImg.isNotEmpty()){
                        mContext?.let {
                            Glide.with(it)
                                .load(bannerImg)
                                .into(bannerImagePager)
                        }!!
                    }else{
                        bannerImagePager.setBackgroundResource(R.drawable.default_banner)

                    }
                    val termDatesAdapter = SubmenuAdapter(termDatesArrayList)
                    TermDatesRecycler.adapter = termDatesAdapter

                }
                else {
                    if (response.body()!!.status == 101) {
                        CommonMethods.showErrorAlert(mContext, "Some error occured", "Alert")
                    }
                }
            }

        })
    }
}