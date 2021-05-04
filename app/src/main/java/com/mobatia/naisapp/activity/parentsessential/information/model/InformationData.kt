package com.mobatia.naisapp.activity.parentsessential.information.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.parentsessential.busservice.model.BannerDetailModel
import com.mobatia.naisapp.activity.parentsessential.model.CommonSubMenuModel

class InformationData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("informations") val busService: List<CommonSubMenuModel>
)