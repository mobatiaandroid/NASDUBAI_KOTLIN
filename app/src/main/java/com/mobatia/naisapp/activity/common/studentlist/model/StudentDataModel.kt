package com.mobatia.naisapp.activity.common.studentlist.model

import com.google.gson.annotations.SerializedName

class StudentDataModel (
    @SerializedName("student_list") val studentListArray: List<StudentListResponse>
    //student_list
)