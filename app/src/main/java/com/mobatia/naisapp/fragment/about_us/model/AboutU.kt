package com.mobatia.naisapp.fragment.about_us.model

import com.google.gson.annotations.SerializedName

data class AboutU(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("tab_type") val tab_type: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("banner_image") val banner_image: String = "",
    @SerializedName("description") val description: String = "")
//    @SerializedName("Item") val Item: ArrayList<Item>)
