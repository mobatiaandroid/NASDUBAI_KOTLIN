package com.mobatia.naisapp.activity.parentsessential.model

import com.google.gson.annotations.SerializedName

class CommonSubMenuModel (
    @SerializedName("id") val id: Int,
    @SerializedName("file") val file: String,
    @SerializedName("submenu") val submenu: String

)