package com.mobatia.naisapp.fragment.primary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.fragment.primary.model.Departmentprimary

internal class PrimaryAdapter (private var primarylist: List<Departmentprimary>) :
    RecyclerView.Adapter<PrimaryAdapter.MyViewHolder>() {
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
        val list = primarylist[position]
        holder.mTitleTxt.text = list.submenu

    }
    override fun getItemCount(): Int {
        return primarylist.size
    }
}