package com.mobatia.naisapp.fragment.ibprogramme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.fragment.ibprogramme.model.IBDetailDataitems

internal class IBDetailsAdapter(private var ibdetailslist: ArrayList<IBDetailDataitems>) :
    RecyclerView.Adapter<IBDetailsAdapter.MyViewHolder>() {
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
        val list = ibdetailslist[position]
        holder.mTitleTxt.text = list.title

    }
    override fun getItemCount(): Int {
        return ibdetailslist.size
    }
}