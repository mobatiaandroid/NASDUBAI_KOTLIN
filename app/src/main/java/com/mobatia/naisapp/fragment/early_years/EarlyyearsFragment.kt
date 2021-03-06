package com.mobatia.naisapp.fragment.early_years

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
import com.mobatia.naisapp.activity.comingup.Early_yearsComingUp
import com.mobatia.naisapp.activity.common_model.CommonResponse
import com.mobatia.naisapp.activity.common_model.Listitems
import com.mobatia.naisapp.activity.early_yearsdetails.Early_yearsDetail
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener

import com.mobatia.naisapp.fragment.primary.adapter.PrimaryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarlyyearsFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var titleTextView: TextView
    lateinit var bannerImagePager: ImageView
    lateinit var earlyyearsRecycler: RecyclerView
    lateinit var progress: ProgressBar
    private lateinit var linearLayoutManager: LinearLayoutManager
    var earlylistAPI = ArrayList<Listitems>()
    var earlyyearslist = ArrayList<Listitems>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_earlyyears, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseUI()

    }

    private fun initialiseUI() {
        mContext = requireContext()
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = view?.findViewById(R.id.titleTextView) as TextView
        bannerImagePager = view!!.findViewById(R.id.bannerImg) as ImageView
        earlyyearsRecycler = view!!.findViewById(R.id.earlyyearsRecycler) as RecyclerView
        progress = view!!.findViewById(R.id.progress) as ProgressBar
        earlyyearsRecycler.layoutManager = linearLayoutManager
        titleTextView.text = getString(R.string.early_years)


        if (CommonMethods.isInternetAvailable(mContext)) {
            getearlyyearslist()

        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }

        earlyyearsRecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                if (position==0)
                {
                    val intent = Intent(activity, Early_yearsComingUp::class.java)
                    activity?.startActivity(intent)
                }
                else
                {
                    val intent = Intent(activity, Early_yearsDetail::class.java)
                    intent.putExtra("id", earlyyearslist[position].id.toString())
                    intent.putExtra("title",earlyyearslist[position].title)
                    activity?.startActivity(intent)
                }

            }

        })
    }

    private fun getearlyyearslist() {
        earlyyearslist = ArrayList()
        earlylistAPI = ArrayList()
        progress.visibility = View.VISIBLE
        val  call:Call<CommonResponse> = ApiClient.getClient.earlyyearslist()
        call.enqueue(object : Callback<CommonResponse>{
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status==100){
                    earlylistAPI.addAll(response.body()!!.data.lists)
                    for (i in 0.. earlylistAPI.size)
                    {
                        if (i==0)
                        {
                            val model=Listitems(0,"Coming Up")
                            earlyyearslist.add(model)
                        }
                        else{
                            val model=Listitems(earlylistAPI[i-1].id, earlylistAPI[i-1].title)
                            earlyyearslist.add(model)
                        }
                    }
                    if (earlylistAPI.size > 0) {
                        earlyyearsRecycler.visibility = View.VISIBLE
                        val early_yearsadapter = PrimaryAdapter(earlyyearslist)
                        earlyyearsRecycler.adapter = early_yearsadapter
                    } else {
                        earlyyearsRecycler.visibility = View.GONE
                        CommonMethods.NodataAlert(mContext, "No Data Available.", "Alert")
                    }


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
            }

        })
    }

}