package com.mobatia.naisapp.fragment.primary

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.comingup.PrimaryComingUp
import com.mobatia.naisapp.activity.primary.PrimaryDetail
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.primary.adapter.PrimaryAdapter
import com.mobatia.naisapp.fragment.primary.model.Departmentprimary
import com.mobatia.naisapp.fragment.primary.model.Primaryresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PrimaryFragment : Fragment() {
    lateinit var mContext: Context

     lateinit var titleTextView: TextView
    lateinit var bannerImagePager: ImageView
    lateinit var primary_recycler: RecyclerView
    lateinit var progress:ProgressBar
    private lateinit var linearLayoutManager: LinearLayoutManager
    var primarylistAPI = ArrayList<Departmentprimary>()
    var primarylist = ArrayList<Departmentprimary>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_primary, container, false)

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
        primary_recycler = view!!.findViewById(R.id.primaryRecycler) as RecyclerView
        progress = view!!.findViewById(R.id.progress) as ProgressBar
        primary_recycler.layoutManager = linearLayoutManager
        titleTextView.text = "Primary"
        if (CommonMethods.isInternetAvailable(mContext)) {
            getprimarylist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }


        primary_recycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                if (position==0)
                {
                    val intent = Intent(activity, PrimaryComingUp::class.java)
                    activity?.startActivity(intent)
                }
                else
                {
                    val intent = Intent(activity, PrimaryDetail::class.java)
                    intent.putExtra("id", primarylist[position].id.toString())
                    intent.putExtra("title",primarylist[position].submenu)
                    activity?.startActivity(intent)
                }

            }

        })
    }

    private fun getprimarylist() {
         primarylist = ArrayList()
         primarylistAPI = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<Primaryresponse> = ApiClient.getClient.primarylist()
        call.enqueue(object : Callback<Primaryresponse> {
            override fun onFailure(call: Call<Primaryresponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<Primaryresponse>,
                response: Response<Primaryresponse>
            ) {
                progress.visibility = View.GONE

                if (response.body()!!.status==100){
                    primarylistAPI.addAll(response.body()!!.data.departmentprimary)
                    for (i in 0.. primarylistAPI.size)
                    {
                      if (i==0)
                      {
                          var model=Departmentprimary(0,"Coming Up")
                          primarylist.add(model)
                      }
                        else{
                          var model=Departmentprimary(primarylistAPI.get(i-1).id,primarylistAPI.get(i-1).submenu)
                          primarylist.add(model)
                      }
                    }
                    val primaryadapter = PrimaryAdapter(primarylist)
                    primary_recycler.adapter = primaryadapter

                    val bannerstring = response.body()!!.data.banner_image
                    if (bannerstring.isNotEmpty()){
                        context?.let {
                            Glide.with(it)
                                .load(bannerstring)
                                .into(bannerImagePager)
                        }!!
                    }else{
                        bannerImagePager.setBackgroundResource(R.drawable.default_banner)

                    }
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