package com.mobatia.naisapp.fragment.ibprogramme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.fragment.ibprogramme.model.Department_Ibprogramme
import com.mobatia.naisapp.fragment.primary.model.Departmentprimary

internal class IB_ProgrammeAdapter (private var iblist: List<Department_Ibprogramme>) :
    RecyclerView.Adapter<IB_ProgrammeAdapter.MyViewHolder>() {
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
        val list = iblist[position]
        holder.mTitleTxt.text = list.submenu

    }
    override fun getItemCount(): Int {
        return iblist.size
    }
}