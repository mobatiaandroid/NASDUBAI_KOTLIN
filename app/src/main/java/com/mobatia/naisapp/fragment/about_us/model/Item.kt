package com.mobatia.naisapp.fragment.about_us.model

import com.google.gson.annotations.SerializedName

data class Item (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String)
