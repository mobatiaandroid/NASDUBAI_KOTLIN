package com.mobatia.naisapp.activity.common_model

import com.google.gson.annotations.SerializedName

data class ComingUpListitems (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("date") val date: String,
    @SerializedName("url") val url: String
)
