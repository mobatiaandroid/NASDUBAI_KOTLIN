package com.mobatia.naisapp.fragment.ibprogramme

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

class IBProgrammeFragment: Fragment() {
    lateinit var mContext: Context
    lateinit var titleTextView: TextView
    lateinit var bannerImagePager: ImageView
    lateinit var ibProgrammeRecycler: RecyclerView
    lateinit var progress: ProgressBar
    private lateinit var linearLayoutManager: LinearLayoutManager
    var ibProgrammeListAPI = ArrayList<Departmentprimary>()
    var ibProgrammelist = ArrayList<Departmentprimary>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ib_programme, container, false)

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
        ibProgrammeRecycler = view!!.findViewById(R.id.ibProgrammeRecycler) as RecyclerView
        progress = view!!.findViewById(R.id.progress) as ProgressBar
        ibProgrammeRecycler.layoutManager = linearLayoutManager
        titleTextView.text = "Primary"
        getIbProgrammeList()

        ibProgrammeRecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                if (position==0)
                {
                    //Comming up activity
                }
                else
                {
                    val intent = Intent(activity, PrimaryDetail::class.java)
                    intent.putExtra("id", ibProgrammelist[position].id.toString())
                    intent.putExtra("title",ibProgrammelist[position].submenu)
                    activity?.startActivity(intent)
                }

            }

        })
    }

    private fun getIbProgrammeList()
    {
        ibProgrammelist = ArrayList()
        ibProgrammeListAPI = ArrayList()
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

                if (response.body()!!.status==100)
                {
                    ibProgrammeListAPI.addAll(response.body()!!.data.departmentprimary)
                    for (i in ibProgrammeListAPI.indices)
                    {
                        if (i==0)
                        {
                            var model= Departmentprimary(0,"Coming Up")
                            ibProgrammelist.add(model)
                        }
                        else{
                            var model= Departmentprimary(ibProgrammeListAPI.get(i).id,ibProgrammeListAPI.get(i).submenu)
                            ibProgrammelist.add(model)
                        }
                    }
                    val primaryadapter = PrimaryAdapter(ibProgrammelist)
                    ibProgrammeRecycler.adapter = primaryadapter

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
                else{
                    if (response.body()!!.status==101)
                    {
                        CommonMethods.showErrorAlert(mContext,"Some error occured","Alert")
                    }
                }
            }

        })
    }

}