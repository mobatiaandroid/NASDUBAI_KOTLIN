package com.mobatia.naisapp.fragment.performing_arts.model

import com.google.gson.annotations.SerializedName

data class PerformingartsData (
    @SerializedName("performing_arts") val performing_arts: List<Performingartsitems>

)
