package com.mobatia.naisapp.activity.ibprogramme_details

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
import com.mobatia.naisapp.activity.common_model.CommonDetailResponse
import com.mobatia.naisapp.activity.common_model.DetailListitems
import com.mobatia.naisapp.activity.home.HomeActivity
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

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class IBDetail : AppCompatActivity() {
    lateinit var mContext: Context
    var id: String = ""
    var title: String = ""
    lateinit var titleTextView: TextView
    lateinit var primaryRecyclerdetails: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress: ProgressBar
    lateinit var back: ImageView
    lateinit var logoclick:ImageView
    var ibdetaillist = ArrayList<DetailListitems>()


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
        primaryRecyclerdetails = findViewById(R.id.primaryRecyclerdetails)
        progress = findViewById(R.id.progress)
        primaryRecyclerdetails.layoutManager = linearLayoutManager
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

        primaryRecyclerdetails.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val urltype = ibdetaillist[position].url
                if (urltype.contains("pdf")) {
                    val intent = Intent(this@IBDetail, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", ibdetaillist[position].url)
                    intent.putExtra("pdf_title", ibdetaillist[position].title)
                    this@IBDetail.startActivity(intent)
                } else {
                    val intent = Intent(this@IBDetail, WebviewLoader::class.java)
                    intent.putExtra("webview_url", ibdetaillist[position].url)
                    this@IBDetail.startActivity(intent)
                }

            }

        })
    }

    private fun ibdetailslist() {
        ibdetaillist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<CommonDetailResponse> = ApiClient.getClient.ibdetails(id.toInt(), 1)
        call.enqueue(object : Callback<CommonDetailResponse> {
            override fun onFailure(call: Call<CommonDetailResponse>, t: Throwable) {
                progress.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<CommonDetailResponse>,
                response: Response<CommonDetailResponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    ibdetaillist.addAll(response.body()!!.data.detaillists)
                    val ib_detailsadapter = PrimaryDetailsAdapter(ibdetaillist)
                    primaryRecyclerdetails.adapter = ib_detailsadapter
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