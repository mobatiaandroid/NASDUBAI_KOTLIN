package com.mobatia.naisapp.fragment.permissionslips.model

import com.google.gson.annotations.SerializedName

class PermissionSlipsDetailModel (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("file") val file: String,
    @SerializedName("status") val status: Int
)