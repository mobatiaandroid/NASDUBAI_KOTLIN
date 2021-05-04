package com.mobatia.naisapp.activity.common.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("validation_errors") val validationErrorArray: List<ValidationError>,
    @SerializedName("data") val data: LoginDataResponse

)
/* @SerializedName("data") val data: List<LoginDataResponse>*/