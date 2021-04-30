package com.mobatia.naisapp.fragment.secondary.model

import com.google.gson.annotations.SerializedName

data class Departmentsecondary (
    @SerializedName("id") val id: Int,
    @SerializedName("submenu") val submenu: String
)
