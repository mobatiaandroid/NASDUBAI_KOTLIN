package com.mobatia.naisapp.activity.Comingup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.activity.home.HomeActivity
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.recyclermanager.OnItemClickListener
import com.mobatia.naisapp.constants.recyclermanager.addOnItemClickListener
import com.mobatia.naisapp.fragment.primary.adapter.ComingupAdapter
import com.mobatia.naisapp.fragment.primary.model.comingup.Primarycomingup
import com.mobatia.naisapp.fragment.primary.model.comingup.Primarycomingupresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrimaryComingUp : AppCompatActivity() {
    lateinit var mContext: Context

    lateinit var titleTextView: TextView
    lateinit var comingsoonrecycler: RecyclerView
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progress: ProgressBar
    lateinit var back: ImageView
    lateinit var logoclick:ImageView
    var primarycominguplist = ArrayList<Primarycomingup>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coming_up)
        InitUI()
        back.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun InitUI() {
        mContext = this
        linearLayoutManager = LinearLayoutManager(mContext)
        titleTextView = findViewById(R.id.titleTextView)
        back = findViewById(R.id.back)
        logoclick = findViewById(R.id.logoclick)
        comingsoonrecycler = findViewById(R.id.comingsoonrecycler)
        progress = findViewById(R.id.progress)
        comingsoonrecycler.layoutManager = linearLayoutManager
        titleTextView.text = "Coming Up"

        if (CommonMethods.isInternetAvailable(mContext)) {
            cominguplist()
        } else {
            CommonMethods.showSuccessInternetAlert(mContext)
        }
        logoclick.setOnClickListener {
            val mIntent = Intent(mContext, HomeActivity::class.java)
            mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(mIntent)
        }

        comingsoonrecycler.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                var webViewComingUpDetail = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<style>\n" +
                        "\n" +
                        "@font-face {\n" +
                        "font-family: SourceSansPro-Semibold;" +
                        "src: url(SourceSansPro-Semibold.ttf);" +

                        "font-family: SourceSansPro-Regular;" +
                        "src: url(SourceSansPro-Regular.ttf);" +
                        "}" +
                        ".title {" +
                        "font-family: SourceSansPro-Regular;" +
                        "font-size:16px;" +
                        "text-align:left;" +
                        "color:	#46C1D0;" +
                        "}" +

                        ".description {" +
                        "font-family: SourceSansPro-Light;" +
                        "text-align:left;" +
                        "font-size:14px;" +
                        "color: #000000;" +
                        "text-align: left;" +
                        "}" +
                        "</style>\n" + "</head>" +
                        "<body>" +
                        "<p class='title'>" + primarycominguplist[position].title + "</p>"
                if (primarycominguplist[position].image.isNotEmpty()) {
                    webViewComingUpDetail =
                        "$webViewComingUpDetail<center><img src='" + primarycominguplist[position].image + "'width='100%', height='auto'>"

                }
                webViewComingUpDetail =
                    "$webViewComingUpDetail<p class='description'>" + primarycominguplist[position].description + "</p>" +
                            "</body>\n</html>"

                val mIntent = Intent(mContext, ComingUpDetailViewActivity::class.java)
                mIntent.putExtra("webViewComingDetail", webViewComingUpDetail)
                mIntent.putExtra("tab_type", "Coming Up")

                startActivity(mIntent)

            }
        })

    }

    private fun cominguplist() {
        primarycominguplist = ArrayList()
        progress.visibility = View.VISIBLE
        val call: Call<Primarycomingupresponse> = ApiClient.getClient.primarycomingup(2)
        call.enqueue(object : Callback<Primarycomingupresponse> {
            override fun onFailure(call: Call<Primarycomingupresponse>, t: Throwable) {
                progress.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<Primarycomingupresponse>,
                response: Response<Primarycomingupresponse>
            ) {
                progress.visibility = View.GONE
                if (response.body()!!.status == 100) {
                    primarycominguplist.addAll(response.body()!!.data)
                    val primarycomingupadapter = ComingupAdapter(primarycominguplist)
                    comingsoonrecycler.adapter = primarycomingupadapter
                } else {
                    if (response.body()!!.status == 101) {
                        CommonMethods.showErrorAlert(mContext, "Some error occured", "Alert")
                    }
                }
            }

        })
    }
}