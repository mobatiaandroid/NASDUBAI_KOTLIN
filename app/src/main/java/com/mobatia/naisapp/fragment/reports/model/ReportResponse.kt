package com.mobatia.naisapp.fragment.reports.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.common.model.ValidationError

class ReportResponse (
    @SerializedName("student_reports") val studentReportsArray: List<StudentReportsModel>
)