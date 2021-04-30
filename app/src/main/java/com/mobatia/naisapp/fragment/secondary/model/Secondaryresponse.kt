package com.mobatia.naisapp.fragment.secondary.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.primary.model.Data

data class Secondaryresponse (

    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: SecondaryData
)

//@SerializedName("validation_errors") val validation_errors: StudentInfoResponseArray)