package com.mobatia.naisapp.activity.parentsessential.busservice.model

import com.google.gson.annotations.SerializedName

class BusServiceResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: BusServiceData
)
