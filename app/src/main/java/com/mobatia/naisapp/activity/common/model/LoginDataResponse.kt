package com.mobatia.naisapp.activity.common.model

import com.google.gson.annotations.SerializedName

data class LoginDataResponse (
    @SerializedName("user_details") val user_details: UserDetailModel,
    @SerializedName("token") val token: String

)