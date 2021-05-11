package com.mobatia.naisapp.activity.staff_departments.model

import com.google.gson.annotations.SerializedName

data class StaffsInDepartment (

    @SerializedName("Leadership Team") val LeadershipTeam: List<LeadershipTeam>,
    @SerializedName("Management") val Management: List<Management>

)
