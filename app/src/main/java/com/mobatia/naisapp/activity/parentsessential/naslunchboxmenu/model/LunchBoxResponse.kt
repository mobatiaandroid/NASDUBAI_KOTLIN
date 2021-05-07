package com.mobatia.naisapp.activity.parentsessential.naslunchboxmenu.model

import com.google.gson.annotations.SerializedName

class LunchBoxResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: LunchBoxData
)
