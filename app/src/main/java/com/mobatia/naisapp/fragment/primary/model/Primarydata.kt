package com.mobatia.naisapp.fragment.primary.model

import com.google.gson.annotations.SerializedName

data class Primarydata (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("sub_menus") val sub_menus: List<Departmentprimary>
)
