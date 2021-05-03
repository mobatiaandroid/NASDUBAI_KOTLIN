package com.mobatia.naisapp.fragment.secondary.model

import com.google.gson.annotations.SerializedName

data class Secondarydetailsresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<SecondaryDetailData>)


//@SerializedName("validation_errors") val validation_errors: StudentInfoResponseArray)
