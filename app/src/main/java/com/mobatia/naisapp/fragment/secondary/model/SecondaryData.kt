package com.mobatia.naisapp.fragment.secondary.model

import com.google.gson.annotations.SerializedName

data class SecondaryData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("sub_menus") val sub_menus: List<Departmentsecondary>
)
