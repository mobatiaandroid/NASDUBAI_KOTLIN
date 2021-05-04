package com.mobatia.naisapp.fragment.reports

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.common.studentlist.model.StudentListModel
import com.mobatia.naisapp.activity.common.studentlist.model.StudentListResponse
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PreferenceManager
import com.mobatia.naisapp.fragment.reports.model.ReportApiModel
import com.mobatia.naisapp.fragment.reports.model.ReportListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var progressDialog: RelativeLayout
    lateinit var studentNameTxt:TextView
    lateinit var imagicon:ImageView
    lateinit var studentSpinner:LinearLayout
    lateinit var recycler_view_list:RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager

    var studentListArrayList = ArrayList<StudentListResponse>()

    lateinit var studentName: String
     var studentId: Int=0
    lateinit var studentImg: String
    lateinit var studentClass: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = requireContext()
        initializeUI()
        if (CommonMethods.isInternetAvailable(mContext)) {
            callStudentListApi()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }
    }

    private fun initializeUI() {
        //Student View
        studentNameTxt=view!!.findViewById(R.id.studentName)
        imagicon=view!!.findViewById(R.id.imagicon)
        studentSpinner=view!!.findViewById(R.id.studentSpinner)
        progressDialog=view!!.findViewById(R.id.progressDialog)
        // List View
        recycler_view_list=view!!.findViewById(R.id.recycler_view_list)
        linearLayoutManager = LinearLayoutManager(mContext)
        recycler_view_list.layoutManager = linearLayoutManager
        recycler_view_list.itemAnimator = DefaultItemAnimator()

    }



    fun callStudentListApi() {
        progressDialog.visibility=View.VISIBLE
        val token = PreferenceManager.getUserCode(mContext)
        val call: Call<StudentListModel> = ApiClient.getClient.studentList("Bearer " + token)
        call.enqueue(object : Callback<StudentListModel> {
            override fun onFailure(call: Call<StudentListModel>, t: Throwable) {
                Log.e("Error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<StudentListModel>,
                response: Response<StudentListModel>
            ) {
                if (response.body()!!.status == 100)
                {
                    studentListArrayList.addAll(response.body()!!.dataArray)
                    if (PreferenceManager.getStudentID(mContext)==0)
                    {
                        Log.e("Empty Img", "Empty")
                        studentName = studentListArrayList.get(0).studentName
                        studentImg = studentListArrayList.get(0).photo
                        studentId = studentListArrayList.get(0).studentId
                        studentClass = studentListArrayList.get(0).section
                        PreferenceManager.setStudentID(mContext, studentId)
                        PreferenceManager.setStudentName(mContext, studentName)
                        PreferenceManager.setStudentPhoto(mContext, studentImg)
                        PreferenceManager.setStudentClass(mContext, studentClass)
                        studentNameTxt.text = studentName
                        if (!studentImg.equals("")) {
                            Glide.with(mContext) //1
                                .load(studentImg)
                                .placeholder(R.drawable.boy)
                                .error(R.drawable.boy)
                                .skipMemoryCache(true) //2
                                .diskCacheStrategy(DiskCacheStrategy.NONE) //3
                                .transform(CircleCrop()) //4
                                .into(imagicon)
                        } else {
                            imagicon.setImageResource(R.drawable.boy)

                        }

                    } else {
                        studentName = PreferenceManager.getStudentName(mContext)!!
                        studentImg = PreferenceManager.getStudentPhoto(mContext)!!
                        studentId = PreferenceManager.getStudentID(mContext)!!
                        studentClass = PreferenceManager.getStudentClass(mContext)!!
                        studentNameTxt.text = studentName
                        if (studentImg != "") {
                            Glide.with(mContext) //1
                                .load(studentImg)
                                .placeholder(R.drawable.boy)
                                .error(R.drawable.boy)
                                .skipMemoryCache(true) //2
                                .diskCacheStrategy(DiskCacheStrategy.NONE) //3
                                .transform(CircleCrop()) //4
                                .into(imagicon)
                        } else {
                            imagicon.setImageResource(R.drawable.boy)
                        }
                    }


                    if (CommonMethods.isInternetAvailable(mContext)) {
                        callReportList()
                    } else {
                        CommonMethods.showSuccessInternetAlert(mContext)
                    }
                }
                else
                {

                }
            }
        })
    }
    private fun callReportList() {
        progressDialog.visibility = View.VISIBLE
        val token = PreferenceManager.getUserCode(mContext)
        val studentid = ReportApiModel(PreferenceManager.getStudentID(mContext)!!.toString())
        val call: Call<ReportListModel> =
            ApiClient.getClient.reportList(studentid, "Bearer " + token)
        call.enqueue(object : Callback<ReportListModel> {
            override fun onFailure(call: Call<ReportListModel>, t: Throwable) {
                progressDialog.visibility = View.GONE
                Log.e("Error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ReportListModel>,
                response: Response<ReportListModel>
            ) {
                progressDialog.visibility = View.GONE
                when (response.body()!!.status) {

                }
            }

        })
    }
}