package com.mobatia.naisapp.fragment.performing_arts.model

import com.google.gson.annotations.SerializedName

data class PerformingartsbannerData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("description") val description: String,
    @SerializedName("contact_email") val contact_email: String

)
