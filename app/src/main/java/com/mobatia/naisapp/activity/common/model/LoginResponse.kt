package com.mobatia.naisapp.activity.common.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.primary.model.Data

data class LoginResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("validation_errors") val validationErrorArray: List<ValidationError>,
    @SerializedName("token") val token: String,
    @SerializedName("data") val data: LoginDataResponse
)
