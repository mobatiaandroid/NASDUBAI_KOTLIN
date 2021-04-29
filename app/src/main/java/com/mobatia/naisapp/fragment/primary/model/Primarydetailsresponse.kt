package com.mobatia.naisapp.fragment.primary.model

import com.google.gson.annotations.SerializedName

data class Primarydetailsresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<PrimaryDetailData>)

//    @SerializedName("data") val data: PrimaryDetailData)

//@SerializedName("validation_errors") val validation_errors: StudentInfoResponseArray)