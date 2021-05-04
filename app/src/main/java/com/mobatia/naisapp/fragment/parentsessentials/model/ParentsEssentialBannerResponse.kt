package com.mobatia.naisapp.fragment.parentsessentials.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.early_years.model.EarlyyearsData

class ParentsEssentialBannerResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ParentsEssentialDataResponse
)