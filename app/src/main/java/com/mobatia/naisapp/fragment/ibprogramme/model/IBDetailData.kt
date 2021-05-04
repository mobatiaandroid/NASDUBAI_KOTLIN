package com.mobatia.naisapp.fragment.ibprogramme.model

import com.google.gson.annotations.SerializedName

data class IBDetailData (
    @SerializedName("details") val details: List<IBDetailDataitems>)

