package com.mobatia.naisapp.fragment.absence

import android.app.Dialog
import android.content.Context
import android.content.Intent
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
import com.mobatia.naisapp.activity.common.studentlist.adapter.StudentListAdapter
import com.mobatia.naisapp.activity.common.studentlist.model.StudentListModel
import com.mobatia.naisapp.activity.common.studentlist.model.StudentListResponse
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PreferenceManager
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.permissionslips.PermissionFormsDetailActivity
import com.mobatia.naisapp.fragment.permissionslips.adapter.PermissionFormListAdapter
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormsApiModel
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormsResponse
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionSlipsDetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbsenceFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var progressDialog: RelativeLayout
    lateinit var studentNameTxt: TextView
    lateinit var imagicon: ImageView
    lateinit var studentSpinner: LinearLayout
    lateinit var recycler_view_list: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager

    var studentListArrayList = ArrayList<StudentListResponse>()
    lateinit var permissionFormsArrayList:ArrayList<PermissionSlipsDetailModel>
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
        return inflater.inflate(R.layout.fragment_absence, container, false)
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
        recycler_view_list=view!!.findViewById(R.id.mAbsenceListView)
        linearLayoutManager = LinearLayoutManager(mContext)
        recycler_view_list.layoutManager = linearLayoutManager
        recycler_view_list.itemAnimator = DefaultItemAnimator()
        val aniRotate: Animation =
            AnimationUtils.loadAnimation(mContext, R.anim.linear_interpolator)
        progressDialog.startAnimation(aniRotate)


        studentSpinner.setOnClickListener(View.OnClickListener {

            showStudentList(mContext,studentListArrayList)
        })

        recycler_view_list.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                val intent = Intent(mContext, PermissionFormsDetailActivity::class.java)
                intent.putExtra("pdf_url", permissionFormsArrayList[position].file)
                intent.putExtra("pdf_title", permissionFormsArrayList[position].title)
                intent.putExtra("permission_slip_id", permissionFormsArrayList[position].id.toString())
                intent.putExtra("status", permissionFormsArrayList[position].status.toString())
                startActivity(intent)

            }

        })

    }



    fun callStudentListApi() {
        progressDialog.visibility= View.VISIBLE
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
                    studentListArrayList.addAll(response.body()!!.dataArray.studentListArray)
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
                    callPermissionFormsListApi()


                }
                else
                {

                }
            }
        })
    }



    fun showStudentList(context: Context, mStudentList: ArrayList<StudentListResponse>) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialogue_student_list)
        var iconImageView = dialog.findViewById(R.id.iconImageView) as ImageView
        var btn_dismiss = dialog.findViewById(R.id.btn_dismiss) as Button
        var studentListRecycler = dialog.findViewById(R.id.recycler_view_social_media) as RecyclerView
        iconImageView.setImageResource(R.drawable.boy)
        //if(mSocialMediaArray.get())
        val sdk = Build.VERSION.SDK_INT
        if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
            btn_dismiss.setBackgroundDrawable(
                mContext.resources.getDrawable(R.drawable.button_new)
            )
        } else {
            btn_dismiss.background = mContext.resources.getDrawable(R.drawable.button_new)
        }

        studentListRecycler.setHasFixedSize(true)
        val llm = LinearLayoutManager(mContext)
        llm.orientation = LinearLayoutManager.VERTICAL
        studentListRecycler.layoutManager = llm
        val studentAdapter = StudentListAdapter(mContext,mStudentList)
        studentListRecycler.adapter = studentAdapter
        btn_dismiss.setOnClickListener()
        {
            dialog.dismiss()
        }
        studentListRecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                // Your logic
                studentName = studentListArrayList[position].studentName
                studentImg = studentListArrayList[position].photo
                studentId = studentListArrayList[position].studentId
                studentClass = studentListArrayList[position].section
                PreferenceManager.setStudentID(mContext, studentId)
                PreferenceManager.setStudentName(mContext, studentName)
                PreferenceManager.setStudentPhoto(mContext, studentImg)
                PreferenceManager.setStudentClass(mContext, studentClass)
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
                progressDialog.visibility = View.VISIBLE
                callPermissionFormsListApi()
                dialog.dismiss()
            }
        })
        dialog.show()
    }


    private fun callPermissionFormsListApi() {
        permissionFormsArrayList= ArrayList()
        progressDialog.visibility = View.VISIBLE
        val token = PreferenceManager.getUserCode(mContext)
        val studentid = PermissionFormsApiModel(PreferenceManager.getStudentID(mContext)!!.toString())
        val call: Call<PermissionFormsResponse> =
            ApiClient.getClient.permissionFormsList(studentid, "Bearer " + token)
        call.enqueue(object : Callback<PermissionFormsResponse> {
            override fun onFailure(call: Call<PermissionFormsResponse>, t: Throwable) {
                progressDialog.visibility = View.GONE
                Log.e("Error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<PermissionFormsResponse>,
                response: Response<PermissionFormsResponse>
            ) {
                progressDialog.visibility = View.GONE
                if (response.body()!!.status==100)
                {
                    if (response.body()!!.data.permissionsSlips.size>0)
                    {
                        permissionFormsArrayList.addAll(response.body()!!.data.permissionsSlips)
                        recycler_view_list.visibility= View.VISIBLE
                        val rAdapter: PermissionFormListAdapter = PermissionFormListAdapter(mContext,permissionFormsArrayList)
                        recycler_view_list.adapter = rAdapter
                    }
                    else
                    {
                        recycler_view_list.visibility= View.GONE
                        CommonMethods.NodataAlert(mContext, "No Data Available.", "Alert")
                    }

                }
            }

        })
    }
}