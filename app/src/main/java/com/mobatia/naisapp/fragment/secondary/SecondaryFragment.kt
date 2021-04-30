package com.mobatia.naisapp.fragment.secondary

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
import com.mobatia.naisapp.activity.Comingup.PrimaryComingUp
import com.mobatia.naisapp.activity.Comingup.SecondaryComingUp
import com.mobatia.naisapp.activity.secondarydetail.Secondarydetails
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.secondary.adapter.SecondaryAdapter
import com.mobatia.naisapp.fragment.secondary.model.Departmentsecondary
import com.mobatia.naisapp.fragment.secondary.model.Secondaryresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondaryFragment : Fragment() {
    lateinit var mContext: Context

    lateinit var titleTextView: TextView
    lateinit var bannerImagePager: ImageView
    lateinit var secondary_recycler: RecyclerView
    lateinit var progress: ProgressBar
    private lateinit var linearLayoutManager: LinearLayoutManager
    var secondarylistAPI = ArrayList<Departmentsecondary>()
    var secondarylist = ArrayList<Departmentsecondary>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_secondary, container, false)
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
        secondary_recycler = view!!.findViewById(R.id.secondaryRecycler) as RecyclerView
        progress = view!!.findViewById(R.id.progress) as ProgressBar
        secondary_recycler.layoutManager = linearLayoutManager
        titleTextView.text = "Secondary"


        if (CommonMethods.isInternetAvailable(mContext)) {
            getsecondarylist()

        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }

        secondary_recycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                if (position==0)
                {
                    val intent = Intent(activity, SecondaryComingUp::class.java)
                    activity?.startActivity(intent)
                }
                else
                {
                    val intent = Intent(activity, Secondarydetails::class.java)
                    intent.putExtra("id", secondarylist[position].id.toString())
                    intent.putExtra("title",secondarylist[position].submenu)
                    activity?.startActivity(intent)
                }

            }

        })
    }

    private fun getsecondarylist() {
        secondarylist = ArrayList()
        secondarylistAPI = ArrayList()
        progress.visibility = View.VISIBLE
        val  call: Call<Secondaryresponse> = ApiClient.getClient.secondarylist()
        call.enqueue(object  : Callback<Secondaryresponse>{
            override fun onFailure(call: Call<Secondaryresponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<Secondaryresponse>,
                response: Response<Secondaryresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status==100){
                    secondarylistAPI.addAll(response.body()!!.data.departmentsecondary)
                    for (i in 0.. secondarylistAPI.size)
                    {
                        if (i==0)
                        {
                            val model=Departmentsecondary(0,"Coming Up")
                            secondarylist.add(model)
                        }
                        else{
                            val model=Departmentsecondary(secondarylistAPI[i-1].id,secondarylistAPI.get(i-1).submenu)
                            secondarylist.add(model)
                        }
                    }
                    val secondaryadapter = SecondaryAdapter(secondarylist)
                    secondary_recycler.adapter = secondaryadapter

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