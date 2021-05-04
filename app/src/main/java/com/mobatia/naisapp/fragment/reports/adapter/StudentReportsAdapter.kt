package com.mobatia.naisapp.fragment.reports.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.fragment.reports.model.ProgressReportModel
import com.mobatia.naisapp.fragment.reports.model.StudentReportsModel

class StudentReportsAdapter (private var mContext: Context, private var reportslist: ArrayList<StudentReportsModel>):
    RecyclerView.Adapter<StudentReportsAdapter.MyViewHolder>() {

    lateinit var clickedurl:String
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var detailArray:ArrayList<ProgressReportModel>
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var accYrLinear: TextView = view.findViewById(R.id.accYrLinear)
        var reportRecycler: RecyclerView = view.findViewById(R.id.recycler_view_list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_report_list, parent, false)
        return MyViewHolder(itemView)    }

    override fun getItemCount(): Int {
        return reportslist.size

    }

    override fun onBindViewHolder(holder: StudentReportsAdapter.MyViewHolder, position: Int) {
        holder.accYrLinear.text = reportslist[position].academic_year
        linearLayoutManager = LinearLayoutManager(mContext)
        holder.reportRecycler.layoutManager = linearLayoutManager
        holder.reportRecycler.itemAnimator = DefaultItemAnimator()
        detailArray=ArrayList()
        if(reportslist[position].progressReportsArray.size>0)
        {
            detailArray.addAll(reportslist[position].progressReportsArray)
            val reportCycleAdapter = ProgressReportAdapter(mContext,detailArray)
            holder.reportRecycler.adapter = reportCycleAdapter
        }

    }
}