package com.mobatia.naisapp.fragment.primary.model.comingup
import com.google.gson.annotations.SerializedName

data class Primarycomingupresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Primarycomingup>)

