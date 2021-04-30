package com.mobatia.naisapp.fragment.ibprogramme.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.primary.model.Departmentprimary

data class IBProgrammeData (
    @SerializedName("banner_image") val banner_image: String,
    @SerializedName("department_ib_programms") val department_ib_programms: List<Department_Ibprogramme>
)
