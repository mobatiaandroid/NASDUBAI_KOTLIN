package com.mobatia.naisapp.activity.staff_departments.model

import com.google.gson.annotations.SerializedName

data class Management (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("about") val about: String,
    @SerializedName("role") val role: String,
    @SerializedName("staff_photo") val staff_photo: String
)
