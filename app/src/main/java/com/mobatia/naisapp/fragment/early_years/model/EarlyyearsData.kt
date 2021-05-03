package com.mobatia.naisapp.fragment.early_years.model

import com.google.gson.annotations.SerializedName
data class EarlyyearsData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("department_early_years") val department_early_years: List<department_Earlyyears>
)
