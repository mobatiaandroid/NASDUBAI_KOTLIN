package com.mobatia.naisapp.activity.staff_departments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.staff_departments.adapter.StaffAdapter
import com.mobatia.naisapp.activity.staff_departments.model.Management
import com.mobatia.naisapp.activity.staff_departments.model.StaffListresponse
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class StaffListActivity : AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var titleTextView: TextView
    lateinit var searchEditText: EditText
    lateinit var stafflistrecycler: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var searchimg: ImageView
    lateinit var logoclick: ImageView
    lateinit var back: ImageView
    var title:String? = ""
    var category_id:String?=""
    private lateinit var linearLayoutManager: LinearLayoutManager
    var deptlistAPI = ArrayList<String>()
    var filteredlist = ArrayList<String>()
    var mStaffDeptList = ArrayList<Management>()
    var mStaffDeptfilteredList = ArrayList<Management>()

    var hashmap: HashMap<String, java.util.ArrayList<Management>> =
        HashMap()
    var filteredHashMap: HashMap<String, java.util.ArrayList<Management>> =
        HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_list)
        initialiseUI()

    }

    private fun initialiseUI() {
        mContext = this
        title = intent.getStringExtra("title")
        category_id = intent.getStringExtra("category_id")

        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = findViewById(R.id.titleTextView)
        searchEditText = findViewById(R.id.searchEditText)
        stafflistrecycler = findViewById(R.id.subjectlistrecycler)
        searchimg = findViewById(R.id.searchimg)
        logoclick = findViewById(R.id.logoclick)
        progress = findViewById(R.id.progress)
        stafflistrecycler.layoutManager = linearLayoutManager
        titleTextView.text = title

        if (CommonMethods.isInternetAvailable(mContext)) {
            getstafflist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }
    }

    private fun getstafflist() {
        deptlistAPI = ArrayList()
        filteredlist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<StaffListresponse> = ApiClient.getClient.staff_departments(category_id!!.toInt())
        call.enqueue(object :Callback<StaffListresponse>{
            override fun onFailure(call: Call<StaffListresponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<StaffListresponse>,
                response: Response<StaffListresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status ==100){
                    deptlistAPI.addAll(response.body()!!.data.departments)
                    val stafflistadapter = StaffAdapter(deptlistAPI)
                    stafflistrecycler.adapter = stafflistadapter

                }

//                val  testdata = response.body()!!.data.departments
//                val  testdatanew = response.body()!!.data.staffs_in_department.get(0).LeadershipTeam.get(0).name



            }

        })
    }
}