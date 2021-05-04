package com.mobatia.naisapp.activity.parentsessential.busservice.model

import com.google.gson.annotations.SerializedName

class BannerDetailModel (

    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("description") val description: String,
    @SerializedName("contact_email") val contact_email: String
)