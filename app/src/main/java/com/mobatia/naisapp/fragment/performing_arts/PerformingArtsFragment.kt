package com.mobatia.naisapp.fragment.performing_arts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.common_model.CommonResponse
import com.mobatia.naisapp.activity.common_model.Listitems
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PdfReaderActivity
import com.mobatia.naisapp.constants.WebviewLoader
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.performing_arts.model.Performingarts_bannerresponse
import com.mobatia.naisapp.fragment.primary.adapter.PrimaryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerformingArtsFragment : Fragment() {
    lateinit var mContext: Context

    lateinit var titleTextView: TextView
    lateinit var bannerImagePager: ImageView
    lateinit var performingartsRecycler: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var mail_icon: ImageView
    lateinit var description: TextView
    private lateinit var linearLayoutManager: LinearLayoutManager
    var performingartslist = ArrayList<Listitems>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_peforming_arts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseUI()
    }

    @SuppressLint("SetTextI18n")
    private fun initialiseUI() {
        mContext = requireContext()
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = view?.findViewById(R.id.titleTextView) as TextView
        bannerImagePager = view!!.findViewById(R.id.bannerImg) as ImageView
        mail_icon = view!!.findViewById(R.id.mail_icon) as ImageView
        description = view!!.findViewById(R.id.description) as TextView
        performingartsRecycler = view!!.findViewById(R.id.performingartsRecycler) as RecyclerView
        progress = view!!.findViewById(R.id.progress) as ProgressBar
        performingartsRecycler.layoutManager = linearLayoutManager
        titleTextView.text = "Performing Arts"

        if (CommonMethods.isInternetAvailable(mContext)) {
            getperformingbanner()
            getperforminglist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }

        performingartsRecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val urltype = performingartslist[position].url
                if (urltype.contains("pdf")) {
                    val intent = Intent(mContext, PdfReaderActivity::class.java)
                    intent.putExtra("pdf_url", performingartslist[position].url)
                    intent.putExtra("pdf_title", performingartslist[position].title)
                    this@PerformingArtsFragment.startActivity(intent)
                } else {
                    val intent = Intent(mContext, WebviewLoader::class.java)
                    intent.putExtra("webview_url", performingartslist[position].url)
                    this@PerformingArtsFragment.startActivity(intent)
                }

            }

        })

    }



    private fun getperformingbanner() {
        progress.visibility = View.VISIBLE
        val call: Call<Performingarts_bannerresponse> = ApiClient.getClient.performiong_arts()
        call.enqueue(object : Callback<Performingarts_bannerresponse> {
            override fun onFailure(call: Call<Performingarts_bannerresponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<Performingarts_bannerresponse>,
                response: Response<Performingarts_bannerresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    val banner_image = response.body()!!.data.banner_image
                    val descriptiontext = response.body()!!.data.description
                    val email_id = response.body()!!.data.contact_email

                    if (descriptiontext.isNotEmpty()) {
                        description.visibility = View.VISIBLE
                        description.text = descriptiontext

                    } else {
                        description.visibility = View.GONE
                    }
                    if (email_id.isNotEmpty()) {
                        mail_icon.visibility = View.VISIBLE

                    } else {
                        mail_icon.visibility = View.GONE
                    }

                    if (banner_image.isNotEmpty()) {
                        context?.let {
                            Glide.with(it)
                                .load(banner_image)
                                .into(bannerImagePager)
                        }!!
                    } else {
                        bannerImagePager.setBackgroundResource(R.drawable.default_banner)

                    }

                } else {
                    if (response.body()!!.status == 101) {
                        CommonMethods.showErrorAlert(mContext, "Some error occured", "Alert")
                    }
                }

            }

        })
    }

    private fun getperforminglist() {
        performingartslist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<CommonResponse> = ApiClient.getClient.performingarts_list(1)
        call.enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    performingartslist.addAll(response.body()!!.data.lists)

                    if (performingartslist.size > 0) {
                        performingartsRecycler.visibility = View.VISIBLE
                    } else {
                        performingartsRecycler.visibility = View.GONE
                        CommonMethods.NodataAlert(mContext, "No Data Available.", "Alert")
                    }
                    val performing_artsadapter = PrimaryAdapter(performingartslist)
                    performingartsRecycler.adapter = performing_artsadapter
                } else {
                    if (response.body()!!.status == 101) {
                        CommonMethods.showErrorAlert(mContext, "Some error occured", "Alert")
                    }
                }

            }

        })
    }
}
