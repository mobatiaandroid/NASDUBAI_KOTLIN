package com.mobatia.naisapp.fragment.performing_arts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.fragment.performing_arts.model.Performingartsitems
import com.mobatia.naisapp.fragment.primary.model.Departmentprimary

internal class PerformingArtsAdapter(private var performingartslist: List<Performingartsitems>) :
    RecyclerView.Adapter<PerformingArtsAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mTitleTxt: TextView = view.findViewById(R.id.listTxtTitle)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.primarylist, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val list = performingartslist[position]
        holder.mTitleTxt.text = list.sub_menu

    }

    override fun getItemCount(): Int {
        return performingartslist.size
    }
}