package com.mobatia.naisapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.constants.InternetCheckClass
import kotlinx.android.synthetic.main.header_bar_fragment.*


class PrimaryFragment : Fragment() {
    lateinit var mContext: Context
    lateinit var mTitleTextView: TextView
    lateinit var bannerImagePager:ImageView
    lateinit var primary_recycler:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseUI()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_primary, container, false)

    }

    @SuppressLint("SetTextI18n")
    private fun initialiseUI() {
        mContext = requireContext()
        mTitleTextView = view!!.findViewById(R.id.titleTextView) as TextView
        bannerImagePager = view!!.findViewById(R.id.bannerImagePager) as ImageView
        primary_recycler = view!!.findViewById(R.id.recyclerView) as RecyclerView
        mTitleTextView.text = "Primary"

        primary_recycler.setOnClickListener {
            val internetCheck = InternetCheckClass.isInternetAvailable(mContext)
            if (internetCheck) {
                getprimarylist()
            } else {
                InternetCheckClass.showSuccessInternetAlert(mContext)
            }
        }
    }

    private fun getprimarylist() {
        TODO("Not yet implemented")
    }

}