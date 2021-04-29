package com.mobatia.naisapp.fragment.primary.model

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("departmentprimary") val departmentprimary: List<Departmentprimary>

)
