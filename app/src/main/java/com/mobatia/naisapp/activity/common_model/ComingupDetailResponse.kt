package com.mobatia.naisapp.activity.common_model

import com.google.gson.annotations.SerializedName

data class ComingupDetailResponse (
    @SerializedName("lists") val cominguplists: List<ComingUpListitems>

)
