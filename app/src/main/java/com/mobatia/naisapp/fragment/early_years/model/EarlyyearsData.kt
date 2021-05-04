package com.mobatia.naisapp.fragment.early_years.model

import com.google.gson.annotations.SerializedName
data class EarlyyearsData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("sub_menus") val sub_menus: List<department_Earlyyears>
)
