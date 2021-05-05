package com.mobatia.naisapp.fragment.permissionslips.model

import com.google.gson.annotations.SerializedName
import com.mobatia.naisapp.fragment.reports.model.StudentReportsModel

class PermissionFormsDataModel (
    @SerializedName("permission_slips") val permissionsSlips: List<PermissionSlipsDetailModel>
)