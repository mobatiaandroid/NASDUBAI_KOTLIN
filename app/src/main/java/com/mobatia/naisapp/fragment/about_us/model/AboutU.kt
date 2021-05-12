package com.mobatia.naisapp.fragment.about_us.model

import com.google.gson.annotations.SerializedName


class AboutU {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("tab_type")
    var tab_type: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("banner_image")
    var banner_image: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("items")
    var items: ArrayList<Item> = arrayListOf()


}







