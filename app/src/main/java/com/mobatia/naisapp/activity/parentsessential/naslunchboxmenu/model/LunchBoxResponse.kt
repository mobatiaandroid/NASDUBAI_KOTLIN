package com.mobatia.naisapp.activity.parentsessential.naslunchboxmenu.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.primary.model.PrimaryDetailData

class LunchBoxResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: LunchBoxData
)
