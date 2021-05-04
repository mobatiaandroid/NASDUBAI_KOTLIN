package com.mobatia.naisapp.fragment.secondary.model

import com.google.gson.annotations.SerializedName

data class SecondaryDetailData (
    @SerializedName("details") val details: List<SecondaryDetailDataitems>)


