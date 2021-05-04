package com.mobatia.naisapp.fragment.parentsessentials.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R

internal class ParentsEssentialListAdapter(private var mContext: Context, private var parentsEssentialArrayList: List<String>) :
    RecyclerView.Adapter<ParentsEssentialListAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.listTxtTitle)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_parents_essential, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = parentsEssentialArrayList[position]
        holder.title.text = movie.toString()

    }
    override fun getItemCount(): Int {

        return parentsEssentialArrayList.size

    }
}