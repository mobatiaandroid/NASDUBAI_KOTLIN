package com.mobatia.naisapp.activity.parentsessential.information.model

import com.google.gson.annotations.SerializedName

class InformationResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: InformationData
)
