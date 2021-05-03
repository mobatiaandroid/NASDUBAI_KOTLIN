package com.mobatia.naisapp.activity.common.model

import com.google.gson.annotations.SerializedName

data class UserDetailModel(
    @SerializedName("id") val id: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("email") val email: String
)