package com.mobatia.naisapp.fragment.ibprogramme.model

import com.google.gson.annotations.SerializedName

data class ibprogrammeresponse (
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: IBProgrammeData
)

//@SerializedName("validation_errors") val validation_errors: StudentInfoResponseArray)

