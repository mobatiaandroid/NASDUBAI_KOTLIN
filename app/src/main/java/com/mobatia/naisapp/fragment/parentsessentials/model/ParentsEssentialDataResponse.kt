package com.mobatia.naisapp.fragment.parentsessentials.model

import com.google.gson.annotations.SerializedName

class ParentsEssentialDataResponse(
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("description") val description: String,
    @SerializedName("contact_email") val contact_email: String
)