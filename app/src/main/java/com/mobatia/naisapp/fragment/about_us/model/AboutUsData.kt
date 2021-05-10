package com.mobatia.naisapp.fragment.about_us.model

import com.google.gson.annotations.SerializedName

data class AboutUsData (
    @SerializedName("about_us") val about_us: List<AboutU>,
    @SerializedName("banner_detail") val banner_detail: BannerDetail
)
