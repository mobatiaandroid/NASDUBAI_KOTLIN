package com.mobatia.naisapp.fragment.contact_us.model

import com.google.gson.annotations.SerializedName

data class Contactdata (
    @SerializedName("description") val description: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("contacts") val contacts: List<Contact>
)
