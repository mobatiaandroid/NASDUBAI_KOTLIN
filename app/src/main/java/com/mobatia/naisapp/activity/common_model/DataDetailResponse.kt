package com.mobatia.naisapp.activity.common_model

import com.google.gson.annotations.SerializedName

data class DataDetailResponse (
    @SerializedName("lists") val detaillists: List<DetailListitems>

)
