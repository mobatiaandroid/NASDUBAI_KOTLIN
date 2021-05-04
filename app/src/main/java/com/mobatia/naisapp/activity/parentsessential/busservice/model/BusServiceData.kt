package com.mobatia.naisapp.activity.parentsessential.busservice.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.parentsessential.model.CommonSubMenuModel

class BusServiceData (
    @SerializedName("banner_detail") val banner_detail: BannerDetailModel,
    @SerializedName("bus_services") val busService: List<CommonSubMenuModel>
)