package com.mobatia.naisapp.fragment.about_us.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.common_model.DetailListitems
import com.mobatia.naisapp.fragment.about_us.model.Item


internal class FacilityAdapter (private var facilitylist: List<Item>) :
    RecyclerView.Adapter<FacilityAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTitleTxt: TextView = view.findViewById(R.id.listTxtTitle)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.primarydetails, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = facilitylist[position]
        holder.mTitleTxt.text = list.title

    }
    override fun getItemCount(): Int {
        return facilitylist.size
    }
}