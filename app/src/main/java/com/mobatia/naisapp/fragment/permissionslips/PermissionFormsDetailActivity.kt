package com.mobatia.naisapp.fragment.permissionslips

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.github.barteksc.pdfviewer.PDFView
import com.mobatia.naisapp.R
import com.mobatia.naisapp.constants.ApiClient
import com.mobatia.naisapp.constants.CommonMethods
import com.mobatia.naisapp.constants.PreferenceManager
import com.mobatia.naisapp.fragment.ibprogramme.adapter.IBDetailsAdapter
import com.mobatia.naisapp.fragment.ibprogramme.model.IBdetailsresponse
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormStatusApiModel
import com.mobatia.naisapp.fragment.permissionslips.model.PermissionFormsStatusResponse
import com.mobatia.naisapp.fragment.reports.model.ReportApiModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
class PermissionFormsDetailActivity : AppCompatActivity() {
    lateinit var back: ImageView
    lateinit var downloadpdf: ImageView
    lateinit var context: Context
    lateinit var pdfviewer: PDFView
    var urltoshow: String = ""
    var title: String = ""
    var status: String = ""
    var permission_slip_id: String = ""
    private val STORAGE_PERMISSION_CODE: Int = 1000
    lateinit var pdfprogress: ProgressBar

    lateinit var acceptRelative:LinearLayout
    lateinit var statusLinear:LinearLayout
    lateinit var acceptImg:ImageView
    lateinit var statusTxt:TextView
    lateinit var accept:Button
    lateinit var notAccept:Button
    var statusValue:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission_forms_detail)
        context = this
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        urltoshow = intent.getStringExtra("pdf_url")
        title = intent.getStringExtra("pdf_title")
        permission_slip_id = intent.getStringExtra("permission_slip_id")
        status = intent.getStringExtra("status")
        back = findViewById(R.id.back)
        downloadpdf = findViewById(R.id.downloadpdf)
        pdfviewer = findViewById(R.id.pdfview)
        pdfprogress = findViewById(R.id.pdfprogress)

        acceptRelative = findViewById(R.id.acceptRelative)
        statusLinear = findViewById(R.id.statusLinear)
        acceptImg = findViewById(R.id.acceptImg)
        statusTxt = findViewById(R.id.statusTxt)
        accept = findViewById(R.id.accept)
        notAccept = findViewById(R.id.notAccept)

        if (status.equals("0"))
        {
            acceptRelative.visibility=View.VISIBLE
            statusLinear.visibility=View.GONE
            acceptImg.setImageResource(R.drawable.approve)
        }
        else if (status.equals("1"))
        {
            acceptRelative.visibility=View.GONE
            statusLinear.visibility=View.VISIBLE
            acceptImg.setImageResource(R.drawable.closed)
            statusTxt.setText("Accepted")
        }
        else
        {
            acceptRelative.visibility= View.GONE
            statusLinear.visibility= View.VISIBLE
            statusTxt.setText("Not Accepted")
        }
        PRDownloader.initialize(applicationContext)
        val fileName = title+".pdf"
        downloadPdfFromInternet(
            urltoshow,
            getRootDirPath(this),
            fileName
        )

        accept.setOnClickListener(View.OnClickListener {
            statusValue="1"
            callStatusChangeAPI(statusValue)

        })

        notAccept.setOnClickListener(View.OnClickListener {
            statusValue="2"
            callStatusChangeAPI(statusValue)
        })
        back.setOnClickListener {
            finish()
        }

        downloadpdf.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        STORAGE_PERMISSION_CODE
                    )
                }

                val fileWithinMyDir = File(getFilepath("$title.pdf"))

                if (fileWithinMyDir.exists()) {
                    fileWithinMyDir.delete()
                    startdownloading()
                    onDownloadComplete()
                } else {
                    startdownloading()
                    onDownloadComplete()
                }
            }
        }
    }

    fun onDownloadComplete() {
        val onComplete = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                pdfprogress.visibility = View.GONE
                Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show()

            }

        }
        registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private fun startdownloading() {
        val request = DownloadManager.Request(Uri.parse(urltoshow))   //URL = URL to download
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Download")
        request.setDescription("The file is downloading...")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$title.pdf")
        pdfprogress.visibility = View.VISIBLE
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

    private fun getFilepath(filename: String): String? {
        return File(
            Environment.getExternalStorageDirectory().absolutePath,
            "/Download/$filename"
        ).path
    }

    fun getRootDirPath(context: Context): String {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file: File = ContextCompat.getExternalFilesDirs(
                context.applicationContext,
                null
            )[0]
            file.absolutePath
        } else {
            context.applicationContext.filesDir.absolutePath
        }
    }

    private fun downloadPdfFromInternet(url: String, dirPath: String, fileName: String) {
        PRDownloader.download(
            url,
            dirPath,
            fileName
        ).build()
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    val downloadedFile = File(dirPath, fileName)

                    showPdfFromFile(downloadedFile)
                }

                override fun onError(error: com.downloader.Error?) {

                }

            })
    }

    private fun showPdfFromFile(file: File) {
        pdfviewer.fromFile(file)
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onPageError { page, _ ->
            }
            .load()
        pdfprogress.visibility = View.GONE

    }


    fun callStatusChangeAPI(statusvalue:String)
    {
        pdfprogress.visibility = View.VISIBLE
        val token = PreferenceManager.getUserCode(context)
        val studentid = PermissionFormStatusApiModel(permission_slip_id,statusvalue)
        val call: Call<PermissionFormsStatusResponse> = ApiClient.getClient.permissionFormStatus(studentid, "Bearer " + token)
        call.enqueue(object : Callback<PermissionFormsStatusResponse> {
            override fun onFailure(call: Call<PermissionFormsStatusResponse>, t: Throwable) {
                pdfprogress.visibility = View.GONE
            }

            override fun onResponse(
                call: Call<PermissionFormsStatusResponse>,
                response: Response<PermissionFormsStatusResponse>
            ) {
                pdfprogress.visibility = View.GONE
                if (response.body()!!.status == 100) {

                    if (statusvalue.equals("0"))
                    {
                        acceptRelative.visibility=View.VISIBLE
                        statusLinear.visibility=View.GONE
                        acceptImg.setImageResource(R.drawable.approve)
                    }
                    else if (statusvalue.equals("1"))
                    {
                        acceptRelative.visibility=View.GONE
                        statusLinear.visibility=View.VISIBLE
                        acceptImg.setImageResource(R.drawable.closed)
                        statusTxt.setText("Accepted")
                    }
                    else
                    {
                        acceptRelative.visibility= View.GONE
                        statusLinear.visibility= View.VISIBLE
                        statusTxt.setText("Not Accepted")
                    }
                }
                else {
                    CommonMethods.NodataAlert(context, "Something went wrong.", "Alert")
                }
            }

        })

    }
}