package com.mobatia.naisapp.fragment.about_us

import android.content.Context
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
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.fragment.about_us.adapter.AboutUsAdapter
import com.mobatia.naisapp.fragment.about_us.model.AboutU
import com.mobatia.naisapp.fragment.about_us.model.AboutUsresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AboutUsFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var weburl: TextView
    lateinit var titleTextView: TextView
    lateinit var aboutusRecycler: RecyclerView
    lateinit var bannerImagePager: ImageView
    lateinit var mail_icon: ImageView
    lateinit var description: TextView
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress: ProgressBar
    var about_uslist = ArrayList<AboutU>()
    var about_uslistAPI = ArrayList<AboutU>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        mContext = requireContext()
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = view?.findViewById(R.id.titleTextView) as TextView
        bannerImagePager = view!!.findViewById(R.id.bannerImagePager) as ImageView
        mail_icon = view!!.findViewById(R.id.mail_icon) as ImageView
        description = view!!.findViewById(R.id.description) as TextView
        weburl = view!!.findViewById(R.id.weburl) as TextView
        aboutusRecycler = view!!.findViewById(R.id.aboutusRecycler) as RecyclerView
        progress = view!!.findViewById(R.id.progress) as ProgressBar
        aboutusRecycler.layoutManager = linearLayoutManager
        titleTextView.text = "About Us"
        if (CommonMethods.isInternetAvailable(mContext)) {
            AboutusList()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }
    }

    private fun AboutusList() {
        about_uslist = ArrayList()
        about_uslistAPI = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<AboutUsresponse> = ApiClient.getClient.aboutuslist()
        call.enqueue(object : Callback<AboutUsresponse> {
            override fun onFailure(call: Call<AboutUsresponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<AboutUsresponse>,
                response: Response<AboutUsresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    val banner_image = response.body()!!.data.banner_detail.banner_image
                    val descriptiontext = response.body()!!.data.banner_detail.description
                    val website_link = response.body()!!.data.banner_detail.website_link
                    val email_id = response.body()!!.data.banner_detail.contact_email

                    if (banner_image.isNotEmpty()) {
                        context?.let {
                            Glide.with(it)
                                .load(banner_image)
                                .into(bannerImagePager)
                        }!!
                    } else {
                        bannerImagePager.setBackgroundResource(R.drawable.default_banner)

                    }

                    if (descriptiontext.isNotEmpty()) {
                        description.visibility = View.VISIBLE
                        description.text = descriptiontext

                    } else {
                        description.visibility = View.GONE
                    }
                    if (website_link.isNotEmpty()) {
                        weburl.visibility = View.VISIBLE
                        weburl.text = website_link

                    } else {
                        weburl.visibility = View.GONE
                    }
                    if (email_id.isNotEmpty()) {
                        mail_icon.visibility = View.VISIBLE

                    } else {
                        mail_icon.visibility = View.GONE
                    }

                    about_uslistAPI.addAll(response.body()!!.data.about_us)
                    for (i in 0..about_uslistAPI.size) {
                        if (i == 0) {
                            val model = AboutU(0, "Staff Directory")
                            about_uslist.add(model)
                        } else {
                            var model = AboutU(
                                about_uslistAPI[i - 1].id,
                                about_uslistAPI[i - 1].tab_type
                            )
                            about_uslist.add(model)
                        }
                    }
                    val about_adapter = AboutUsAdapter(about_uslist)
                    aboutusRecycler.adapter = about_adapter
                } else {
                    if (response.body()!!.status == 101) {
                        CommonMethods.showErrorAlert(mContext, "Some error occured", "Alert")
                    }
                }
            }

        })
    }
}