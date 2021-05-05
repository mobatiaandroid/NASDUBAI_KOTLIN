package com.mobatia.naisapp.fragment.permissionslips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionSlipsDetailModel
import com.mobatia.naisapp.fragment.reports.adapter.ProgressReportAdapter
import com.mobatia.naisapp.fragment.reports.adapter.StudentReportsAdapter
import com.mobatia.naisapp.fragment.reports.model.ProgressReportModel
import com.mobatia.naisapp.fragment.reports.model.StudentReportsModel

class PermissionFormListAdapter (private var mContext: Context, private var permissionFormList: ArrayList<PermissionSlipsDetailModel>):
    RecyclerView.Adapter<PermissionFormListAdapter.MyViewHolder>() {

    lateinit var clickedurl:String
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pdfTitle: TextView = view.findViewById(R.id.pdfTitle)
        var imageIcon: ImageView = view.findViewById(R.id.imageIcon)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_permission_forms_list, parent, false)
        return MyViewHolder(itemView)    }

    override fun getItemCount(): Int {
        return permissionFormList.size

    }

    override fun onBindViewHolder(holder: PermissionFormListAdapter.MyViewHolder, position: Int) {

           holder.pdfTitle.setText(permissionFormList.get(position).title)
           holder.imageIcon.visibility=View.GONE

    }
}