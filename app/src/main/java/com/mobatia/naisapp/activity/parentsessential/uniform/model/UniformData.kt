package com.mobatia.naisapp.activity.parentsessential.uniform.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.parentsessential.model.CommonSubMenuModel

class UniformData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("uniforms") val uniformList: List<CommonSubMenuModel>
)