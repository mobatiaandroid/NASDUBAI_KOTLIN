package com.mobatia.naisapp.fragment.about_us.model

import com.google.gson.annotations.SerializedName

data class BannerDetail (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("description") val description: String,
    @SerializedName("contact_email") val contact_email: String,
    @SerializedName("website_link") val website_link: String
    )
