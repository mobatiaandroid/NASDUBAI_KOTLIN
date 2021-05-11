package com.mobatia.naisapp.activity.staff_departments.model

import com.google.gson.annotations.SerializedName

data class StaffData (
    @SerializedName("staffs_in_department") val staffs_in_department: ArrayList<StaffsInDepartment>,
    @SerializedName("departments") val departments: List<String>

)
