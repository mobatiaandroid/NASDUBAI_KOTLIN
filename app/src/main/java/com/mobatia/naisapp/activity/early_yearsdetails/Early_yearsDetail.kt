package com.mobatia.naisapp.activity.early_yearsdetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PdfReaderActivity
import com.mobatia.naisapp.constants.WebviewLoader
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.ibprogramme.adapter.IBDetailsAdapter
import com.mobatia.naisapp.fragment.ibprogramme.model.IBDetailData
import com.mobatia.naisapp.fragment.ibprogramme.model.IBDetailDataitems
import com.mobatia.naisapp.fragment.ibprogramme.model.IBdetailsresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Early_yearsDetail : AppCompatActivity() {
    lateinit var mContext: Context
    var id: String = ""
    var title: String = ""
    lateinit var titleTextView: TextView
    lateinit var earlyRecyclerdetails: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress: ProgressBar
    lateinit var back: ImageView
    lateinit var logoclick:ImageView
    var earlydetaillist = ArrayList<IBDetailDataitems>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary_detail)
        InitUI()
        back.setOnClickListener {
            finish()
        }

    }


    private fun InitUI() {
        mContext = this
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = findViewById(R.id.titleTextView)
        back = findViewById(R.id.back)
        logoclick = findViewById(R.id.logoclick)
        earlyRecyclerdetails = findViewById(R.id.primaryRecyclerdetails)
        progress = findViewById(R.id.progress)
        earlyRecyclerdetails.layoutManager = linearLayoutManager
        titleTextView.text = title

        if (CommonMethods.isInternetAvailable(mContext)) {
            ibdetailslist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }
        logoclick.setOnClickListener {
            val mIntent = Intent(mContext, HomeActivity::class.java)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(mIntent)
        }

        earlyRecyclerdetails.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val urltype = earlydetaillist[position].file
                if (urltype.contains("pdf")) {
                    val intent = Intent(this@Early_yearsDetail, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", earlydetaillist[position].file)
                    intent.putExtra("pdf_title", earlydetaillist[position].title)
                    this@Early_yearsDetail.startActivity(intent)
                } else {
                    val intent = Intent(this@Early_yearsDetail, WebviewLoader::class.java)
                    intent.putExtra("webview_url", earlydetaillist[position].file)
                    this@Early_yearsDetail.startActivity(intent)
                }

            }

        })
    }

    private fun ibdetailslist() {
        earlydetaillist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<IBdetailsresponse> = ApiClient.getClient.earlydetails(id.toInt(), 1)
        call.enqueue(object : Callback<IBdetailsresponse> {
            override fun onFailure(call: Call<IBdetailsresponse>, t: Throwable) {
                progress.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<IBdetailsresponse>,
                response: Response<IBdetailsresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    earlydetaillist.addAll(response.body()!!.data.details)
                    val ib_detailsadapter = IBDetailsAdapter(earlydetaillist)
                    earlyRecyclerdetails.adapter = ib_detailsadapter
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