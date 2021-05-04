package com.mobatia.naisapp.fragment.secondary.model

import com.google.gson.annotations.SerializedName

data class SecondaryDetailDataitems(

    @SerializedName("id") val id: Int,
    @SerializedName("file") val file: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String

)
