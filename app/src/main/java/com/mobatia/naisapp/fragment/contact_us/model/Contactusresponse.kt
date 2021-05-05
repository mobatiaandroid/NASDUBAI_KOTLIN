package com.mobatia.naisapp.fragment.contact_us.model
import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.activity.common.model.ValidationError

data class Contactusresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("validation_errors") val validationErrorArray: List<ValidationError>,
    @SerializedName("data") val contactusdata: Contactdata
)
