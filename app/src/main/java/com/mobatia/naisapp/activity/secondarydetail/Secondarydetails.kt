package com.mobatia.naisapp.activity.secondarydetail

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
import com.mobatia.naisapp.fragment.primary.adapter.PrimaryDetailsAdapter
import com.mobatia.naisapp.fragment.primary.model.PrimaryDetailData
import com.mobatia.naisapp.fragment.secondary.adapter.SecondaryDetailsAdapter
import com.mobatia.naisapp.fragment.secondary.model.SecondaryDetailData
import com.mobatia.naisapp.fragment.secondary.model.Secondarydetailsresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Secondarydetails : AppCompatActivity() {
    lateinit var mContext: Context
    var id: String = ""
    var title: String = ""
    lateinit var titleTextView: TextView
    lateinit var secondaryRecyclerdetails: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress: ProgressBar
    lateinit var back: ImageView
    var secondarydetaillist = ArrayList<SecondaryDetailData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondarydetails)
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
        secondaryRecyclerdetails = findViewById(R.id.secondaryRecyclerdetails)
        progress = findViewById(R.id.progress)
        secondaryRecyclerdetails.layoutManager = linearLayoutManager
        titleTextView.text = title

        if (CommonMethods.isInternetAvailable(mContext)) {
            secondarydetailslist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }


        secondaryRecyclerdetails.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val urltype = secondarydetaillist[position].file
                if (urltype.contains("pdf")) {
                    val intent = Intent(this@Secondarydetails, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", secondarydetaillist[position].file)
                    intent.putExtra("pdf_title", secondarydetaillist[position].title)
                    this@Secondarydetails.startActivity(intent)
                } else {
                    val intent = Intent(this@Secondarydetails, WebviewLoader::class.java)
                    intent.putExtra("webview_url", secondarydetaillist[position].file)
                    this@Secondarydetails.startActivity(intent)
                }

            }

        })
    }

    private fun secondarydetailslist() {
        secondarydetaillist = ArrayList()
        progress.visibility = View.VISIBLE
        val  call:Call<Secondarydetailsresponse> = ApiClient.getClient.secondarydetails(id.toInt(),1)
        call.enqueue(object :Callback<Secondarydetailsresponse>{
            override fun onFailure(call: Call<Secondarydetailsresponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<Secondarydetailsresponse>,
                response: Response<Secondarydetailsresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    secondarydetaillist.addAll(response.body()!!.data)
                    val secondaryDetailsAdapter = SecondaryDetailsAdapter(secondarydetaillist)
                    secondaryRecyclerdetails.adapter = secondaryDetailsAdapter
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