package com.mobatia.naisapp.activity.parentsessential.termdates.model

import com.google.gson.annotations.SerializedName

class TermDatesResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: TermDatesData
)
