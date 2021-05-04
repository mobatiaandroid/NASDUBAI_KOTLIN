package com.mobatia.naisapp.fragment.primary.model.comingup

import com.google.gson.annotations.SerializedName

data class Primarycomingup(
    @SerializedName("coming_ups") val coming_ups: List<PrimaryComingupitems>)
