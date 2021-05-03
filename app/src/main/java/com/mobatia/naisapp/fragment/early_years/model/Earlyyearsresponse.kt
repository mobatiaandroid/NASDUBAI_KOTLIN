package com.mobatia.naisapp.fragment.early_years.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.secondary.model.SecondaryData

data class Earlyyearsresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: EarlyyearsData
)