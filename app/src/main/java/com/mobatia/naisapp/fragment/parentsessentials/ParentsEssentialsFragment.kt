package com.mobatia.naisapp.fragment.parentsessentials

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.comingup.PrimaryComingUp
import com.mobatia.naisapp.activity.parentsessential.busservice.BusServiceActivity
import com.mobatia.naisapp.activity.parentsessential.information.InformationActivity
import com.mobatia.naisapp.activity.parentsessential.naslunchboxmenu.NasLunchBoxActivity
import com.mobatia.naisapp.activity.parentsessential.termdates.TermDatesActivity
import com.mobatia.naisapp.activity.parentsessential.uniform.UniformActivity
import com.mobatia.naisapp.activity.primary.PrimaryDetail
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.ibprogramme.adapter.IB_ProgrammeAdapter
import com.mobatia.naisapp.fragment.ibprogramme.model.Department_Ibprogramme
import com.mobatia.naisapp.fragment.ibprogramme.model.ibprogrammeresponse
import com.mobatia.naisapp.fragment.parentsessentials.adapter.ParentsEssentialListAdapter
import com.mobatia.naisapp.fragment.parentsessentials.model.ParentsEssentialBannerResponse
import com.mobatia.naisapp.fragment.primary.adapter.PrimaryAdapter
import com.mobatia.naisapp.fragment.primary.model.Departmentprimary
import com.mobatia.naisapp.fragment.primary.model.Primaryresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParentsEssentialsFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var bannerImagePager:ImageView
    lateinit var sendEmail:ImageView
    lateinit var descriptionTV:TextView
    lateinit var mListView:RecyclerView
    lateinit var progressDialog:RelativeLayout
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var parentsEssentialArrayList : ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parents_essential, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseUI()
        if (CommonMethods.isInternetAvailable(mContext)) {
            callParentsEssentialBanner()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initialiseUI() {
        mContext = requireContext()
        bannerImagePager=view!!.findViewById(R.id.bannerImagePager)
        sendEmail=view!!.findViewById(R.id.sendEmail)
        descriptionTV=view!!.findViewById(R.id.descriptionTV)
        mListView=view!!.findViewById(R.id.mListView)
        progressDialog=view!!.findViewById(R.id.progressDialog)
        linearLayoutManager = LinearLayoutManager(mContext)
        mListView.layoutManager = linearLayoutManager
        val aniRotate: Animation =
            AnimationUtils.loadAnimation(mContext, R.anim.linear_interpolator)
        progressDialog.startAnimation(aniRotate)
        parentsEssentialArrayList= ArrayList()
        parentsEssentialArrayList.add("Term Dates")
        parentsEssentialArrayList.add("Uniform")
        parentsEssentialArrayList.add("NAS Lunch Box Menu")
        parentsEssentialArrayList.add("Bus Service")
        parentsEssentialArrayList.add("Information")

        mListView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                 if (position==0)
                 {
                     val intent = Intent(activity, TermDatesActivity::class.java)
                     activity?.startActivity(intent)
                 }
                 else if (position==1)
                 {
                     val intent = Intent(activity, UniformActivity::class.java)
                     activity?.startActivity(intent)
                 }
                else if (position==2)
                 {
                     val intent = Intent(activity, NasLunchBoxActivity::class.java)
                     activity?.startActivity(intent)
                 }
                 else if (position==3)
                 {
                     val intent = Intent(activity, BusServiceActivity::class.java)
                     activity?.startActivity(intent)
                 }  else if (position==4)
                 {
                     val intent = Intent(activity, InformationActivity::class.java)
                     activity?.startActivity(intent)
                 }

            }

        })
    }

    private fun callParentsEssentialBanner() {
        progressDialog.visibility = View.VISIBLE
        val call: Call<ParentsEssentialBannerResponse> = ApiClient.getClient.parentsEssentialBanner()
        call.enqueue(object : Callback<ParentsEssentialBannerResponse> {
            override fun onFailure(call: Call<ParentsEssentialBannerResponse>, t: Throwable) {
                progressDialog.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<ParentsEssentialBannerResponse>,
                response: Response<ParentsEssentialBannerResponse>
            ) {
                progressDialog.visibility = View.GONE

                if (response.body()!!.status == 100) {

                  val email:String=response.body()!!.data.contact_email
                  val description:String=response.body()!!.data.description
                  val bannerImage:String=response.body()!!.data.banner_image

                    if (bannerImage.isNotEmpty()){
                        context?.let {
                            Glide.with(it)
                                .load(bannerImage)
                                .into(bannerImagePager)
                        }!!
                    }else{
                        bannerImagePager.setBackgroundResource(R.drawable.default_banner)

                    }
                    if (email.equals(""))
                    {
                        sendEmail.visibility=View.GONE
                    }
                    else{
                        sendEmail.visibility=View.VISIBLE
                    }
                    if (description.equals(""))
                    {
                        descriptionTV.visibility=View.GONE
                    }
                    else{
                        descriptionTV.visibility=View.VISIBLE
                        descriptionTV.setText(description)
                    }

                }

                else {
                    if (response.body()!!.status == 101) {
                        CommonMethods.showErrorAlert(mContext, "Some error occured", "Alert")
                    }
                }
                val parentsEssentialAdapter = ParentsEssentialListAdapter(mContext,parentsEssentialArrayList)
                mListView.adapter = parentsEssentialAdapter
            }

        })
    }

}