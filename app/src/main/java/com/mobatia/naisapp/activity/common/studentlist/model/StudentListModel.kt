package com.mobatia.naisapp.activity.common.studentlist.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.common.model.LoginDataResponse
import com.mobatia.naisapp.activity.common.model.ValidationError

class StudentListModel (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("validation_errors") val validationErrorArray: List<ValidationError>,
    @SerializedName("data") val dataArray: List<StudentListResponse>
)