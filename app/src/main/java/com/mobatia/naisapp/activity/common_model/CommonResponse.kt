package com.mobatia.naisapp.activity.common_model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.common.model.ValidationError

data class CommonResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("validation_errors") val validationErrorArray: List<ValidationError>,
    @SerializedName("data") val data: DataResponse
)