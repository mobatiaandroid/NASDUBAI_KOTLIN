package com.mobatia.naisapp.activity.staff_directory

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.comingup.PrimaryComingUp
import com.mobatia.naisapp.activity.common_model.CommonResponse
import com.mobatia.naisapp.activity.common_model.Listitems
import com.mobatia.naisapp.activity.primary.PrimaryDetail
import com.mobatia.naisapp.activity.staff_departments.StaffListActivity
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.primary.adapter.PrimaryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class StaffDirectory : AppCompatActivity() {
    lateinit var mContext: Context
    lateinit var titleTextView: TextView
    lateinit var searchEditText: EditText
    lateinit var subjectlistrecycler: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var searchimg: ImageView
    lateinit var logoclick: ImageView
    lateinit var back: ImageView
    private lateinit var linearLayoutManager: LinearLayoutManager
    var subjectlistAPI = ArrayList<Listitems>()
    var filteredlist = ArrayList<Listitems>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_directory)
        initialiseUI()

    }

    @SuppressLint("SetTextI18n")
    private fun initialiseUI() {
        mContext = this
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = findViewById(R.id.titleTextView)
        searchEditText = findViewById(R.id.searchEditText)
        subjectlistrecycler = findViewById(R.id.subjectlistrecycler)
        searchimg = findViewById(R.id.searchimg)
        logoclick = findViewById(R.id.logoclick)
        progress = findViewById(R.id.progress)
        subjectlistrecycler.layoutManager = linearLayoutManager
        titleTextView.text = "Staff Directory"

        if (CommonMethods.isInternetAvailable(mContext)) {
            getsubjectlist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }
        subjectlistrecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {

                if (filteredlist.size>0){
                    val intent = Intent(mContext, StaffListActivity::class.java)
                    intent.putExtra("category_id", filteredlist[position].id.toString())
                    intent.putExtra("title", filteredlist[position].title)
                    mContext.startActivity(intent)
                }
                else{
                    val intent = Intent(mContext, StaffListActivity::class.java)
                    intent.putExtra("category_id", subjectlistAPI[position].id.toString())
                    intent.putExtra("title", subjectlistAPI[position].title)
                    mContext.startActivity(intent)
                }

            }

        })

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (subjectlistAPI.size > 0) {
                    filteredlist = java.util.ArrayList()
                    for (i in subjectlistAPI.indices) {
                        if (subjectlistAPI[i].title
                                .toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase())
                        ) {
                            filteredlist.add(subjectlistAPI[i])
                        }
                    }
                    val sportsEventListAdapters =
                        PrimaryAdapter(filteredlist)
                    subjectlistrecycler.adapter = sportsEventListAdapters
                    if (searchEditText.text.toString()
                            .equals("", ignoreCase = true)
                    ) {
                        val sportsEventListAdapter =
                            PrimaryAdapter(subjectlistAPI)
                        subjectlistrecycler.adapter = sportsEventListAdapter
                        filteredlist = java.util.ArrayList()
                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }


    private fun getsubjectlist() {
        subjectlistAPI = ArrayList()
        filteredlist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<CommonResponse> = ApiClient.getClient.staff_categories(1)
        call.enqueue(object : Callback<CommonResponse> {
            override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                progress.visibility = View.GONE

            }

            override fun onResponse(
                call: Call<CommonResponse>,
                response: Response<CommonResponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    subjectlistAPI.addAll(response.body()!!.data.lists)
                    val primaryadapter = PrimaryAdapter(subjectlistAPI)
                    subjectlistrecycler.adapter = primaryadapter

                }

            }

        })

    }
}