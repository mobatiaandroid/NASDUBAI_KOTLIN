package com.mobatia.naisapp.fragment.ibprogramme.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.primary.model.PrimaryDetailData

data class IBdetailsresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: IBDetailData)


//@SerializedName("validation_errors") val validation_errors: StudentInfoResponseArray)