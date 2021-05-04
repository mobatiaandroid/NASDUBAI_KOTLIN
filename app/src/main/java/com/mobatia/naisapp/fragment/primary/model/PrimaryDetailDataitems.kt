package com.mobatia.naisapp.fragment.primary.model

import com.google.gson.annotations.SerializedName

data class PrimaryDetailDataitems(

    @SerializedName("id") val id: Int,
    @SerializedName("file") val file: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String

)
