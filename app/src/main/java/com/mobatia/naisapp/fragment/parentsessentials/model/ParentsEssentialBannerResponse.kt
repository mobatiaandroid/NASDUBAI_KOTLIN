package com.mobatia.naisapp.fragment.parentsessentials.model

import com.google.gson.annotations.SerializedName

class ParentsEssentialBannerResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ParentsEssentialDataResponse
)