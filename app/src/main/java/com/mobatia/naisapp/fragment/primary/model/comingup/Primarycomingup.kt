package com.mobatia.naisapp.fragment.primary.model.comingup

import com.google.gson.annotations.SerializedName

data class Primarycomingup(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("date") val date: String = "",
    @SerializedName("image") val image: String = ""
)