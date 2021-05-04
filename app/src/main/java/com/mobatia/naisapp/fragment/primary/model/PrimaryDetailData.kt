package com.mobatia.naisapp.fragment.primary.model

import com.google.gson.annotations.SerializedName

data class PrimaryDetailData(

    @SerializedName("details") val details: List<PrimaryDetailDataitems>)

