package com.mobatia.naisapp.fragment.permissionslips.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.common.model.ValidationError
import com.mobatia.naisapp.fragment.reports.model.ReportResponse

class PermissionFormsResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("validation_errors") val validationErrorArray: List<ValidationError>,
    @SerializedName("data") val data: PermissionFormsDataModel
)