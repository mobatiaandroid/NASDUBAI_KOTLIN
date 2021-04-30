package com.mobatia.naisapp.activity.primary

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
import com.mobatia.naisapp.fragment.primary.adapter.PrimaryDetailsAdapter
import com.mobatia.naisapp.fragment.primary.model.PrimaryDetailData
import com.mobatia.naisapp.fragment.primary.model.Primarydetailsresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PrimaryDetail : AppCompatActivity() {
    lateinit var mContext: Context
    var id: String = ""
    var title: String = ""
    lateinit var titleTextView: TextView
    lateinit var primaryRecyclerdetails: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress: ProgressBar
    lateinit var back: ImageView
    lateinit var logoclick:ImageView
    var primarydetaillist = ArrayList<PrimaryDetailData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary_detail)
        InitUI()
        back.setOnClickListener {
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
        id = intent.getStringExtra("id")
        title = intent.getStringExtra("title")
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = findViewById(R.id.titleTextView)
        back = findViewById(R.id.back)
        logoclick = findViewById(R.id.logoclick)
        primaryRecyclerdetails = findViewById(R.id.primaryRecyclerdetails)
        progress = findViewById(R.id.progress)
        primaryRecyclerdetails.layoutManager = linearLayoutManager
        titleTextView.text = title

        if (CommonMethods.isInternetAvailable(mContext)) {
            primarydetailslist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }


        primaryRecyclerdetails.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val urltype = primarydetaillist[position].file
                if (urltype.contains("pdf")) {
                    val intent = Intent(this@PrimaryDetail, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", primarydetaillist[position].file)
                    intent.putExtra("pdf_title", primarydetaillist[position].title)
                    this@PrimaryDetail.startActivity(intent)
                } else {
                    val intent = Intent(this@PrimaryDetail, WebviewLoader::class.java)
                    intent.putExtra("webview_url", primarydetaillist[position].file)
                    this@PrimaryDetail.startActivity(intent)
                }

            }

        })
    }

    private fun primarydetailslist() {
        primarydetaillist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<Primarydetailsresponse> = ApiClient.getClient.primarydetails(id.toInt(), 1)
        call.enqueue(object : Callback<Primarydetailsresponse> {
            override fun onFailure(call: Call<Primarydetailsresponse>, t: Throwable) {
                progress.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<Primarydetailsresponse>,
                response: Response<Primarydetailsresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    primarydetaillist.addAll(response.body()!!.data)
                    val primaryadapter = PrimaryDetailsAdapter(primarydetaillist)
                    primaryRecyclerdetails.adapter = primaryadapter
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