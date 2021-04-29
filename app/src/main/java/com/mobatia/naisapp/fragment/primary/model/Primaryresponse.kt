package com.mobatia.naisapp.fragment.primary.model

import com.google.gson.annotations.SerializedName

data class Primaryresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Data)

//@SerializedName("validation_errors") val validation_errors: StudentInfoResponseArray)