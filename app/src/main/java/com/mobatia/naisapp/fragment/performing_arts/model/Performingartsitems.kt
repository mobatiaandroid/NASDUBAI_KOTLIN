package com.mobatia.naisapp.fragment.performing_arts.model

import com.google.gson.annotations.SerializedName

data class Performingartsitems(

    @SerializedName("id") val id: Int,
    @SerializedName("sub_menu") val sub_menu: String,
    @SerializedName("file") val file: String

)
