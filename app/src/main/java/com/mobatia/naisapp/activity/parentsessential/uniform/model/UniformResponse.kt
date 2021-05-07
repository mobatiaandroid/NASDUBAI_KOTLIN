package com.mobatia.naisapp.activity.parentsessential.uniform.model

import com.google.gson.annotations.SerializedName

class UniformResponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: UniformData
)
