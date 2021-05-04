package com.mobatia.naisapp.activity.parentsessential.termdates.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.parentsessential.model.CommonSubMenuModel

class TermDatesData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("term_dates") val termDatesArrayList: List<CommonSubMenuModel>
)