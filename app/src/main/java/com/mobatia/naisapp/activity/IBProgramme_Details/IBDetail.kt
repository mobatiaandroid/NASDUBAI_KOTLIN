package com.mobatia.naisapp.activity.IBProgramme_Details

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
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PdfReaderActivity
import com.mobatia.naisapp.constants.WebviewLoader
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.ibprogramme.adapter.IBDetailsAdapter
import com.mobatia.naisapp.fragment.ibprogramme.model.IBDetailData
import com.mobatia.naisapp.fragment.ibprogramme.model.IBdetailsresponse
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
    var ibdetaillist = ArrayList<IBDetailData>()


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
        primaryRecyclerdetails = findViewById(R.id.primaryRecyclerdetails)
        progress = findViewById(R.id.progress)
        primaryRecyclerdetails.layoutManager = linearLayoutManager
        titleTextView.text = title

        if (CommonMethods.isInternetAvailable(mContext)) {
            ibdetailslist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }


        primaryRecyclerdetails.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val urltype = ibdetaillist[position].file
                if (urltype.contains("pdf")) {
                    val intent = Intent(this@IBDetail, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", ibdetaillist[position].file)
                    intent.putExtra("pdf_title", ibdetaillist[position].title)
                    this@IBDetail.startActivity(intent)
                } else {
                    val intent = Intent(this@IBDetail, WebviewLoader::class.java)
                    intent.putExtra("webview_url", ibdetaillist[position].file)
                    this@IBDetail.startActivity(intent)
                }

            }

        })
    }

    private fun ibdetailslist() {
        ibdetaillist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<IBdetailsresponse> = ApiClient.getClient.ibdetails(id.toInt(), 1)
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
                    ibdetaillist.addAll(response.body()!!.data)
                    val ib_detailsadapter = IBDetailsAdapter(ibdetaillist)
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