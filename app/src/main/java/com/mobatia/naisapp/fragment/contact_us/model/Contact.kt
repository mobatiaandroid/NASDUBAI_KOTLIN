package com.mobatia.naisapp.fragment.contact_us.model

import com.google.gson.annotations.SerializedName

data class Contact (
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String
    )
