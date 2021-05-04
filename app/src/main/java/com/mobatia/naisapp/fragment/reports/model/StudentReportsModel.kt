package com.mobatia.naisapp.fragment.reports.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.common.model.ValidationError

class StudentReportsModel (
    @SerializedName("academic_year") val academic_year: String,
    @SerializedName("progress_reports") val progressReportsArray: List<ProgressReportModel>
)