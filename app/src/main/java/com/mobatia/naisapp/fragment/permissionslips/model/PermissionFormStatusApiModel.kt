package com.mobatia.naisapp.fragment.permissionslips.model

import com.google.gson.annotations.SerializedName

class PermissionFormStatusApiModel (
    @SerializedName("permission_slip_id") val permission_slip_id: String,
    @SerializedName("status") val status: String
)