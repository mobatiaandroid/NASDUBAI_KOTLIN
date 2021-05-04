package com.mobatia.naisapp.fragment.reports.model

import com.google.gson.annotations.SerializedName

class ProgressReportModel (
    @SerializedName("reporting_cycle") val reporting_cycle: String,
    @SerializedName("file") val file: String,
    @SerializedName("status") val status: Int
)