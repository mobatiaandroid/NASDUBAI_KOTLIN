package com.mobatia.naisapp.activity.parentsessential.naslunchboxmenu.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.parentsessential.model.CommonSubMenuModel

class LunchBoxData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("lunch_box_menus") val lunchBoxList: List<CommonSubMenuModel>
)