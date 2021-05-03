package com.mobatia.naisapp.fragment.primary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.fragment.primary.model.comingup.Primarycomingup

internal class ComingupAdapter (private var cominguplist: List<Primarycomingup>) :
    RecyclerView.Adapter<ComingupAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTitleTxt: TextView = view.findViewById(R.id.listTxtTitle)
        var  listTxtDate:TextView = view.findViewById(R.id.listTxtDate)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cominguplayout, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = cominguplist[position]
        holder.mTitleTxt.text = list.title
        holder.listTxtDate.text = CommonMethods.dateParsingTo_dd_MMM_yyyy(list.date)

    }
    override fun getItemCount(): Int {
        return cominguplist.size
    }
}