package com.fridayhouse.mimblu_assignment.data

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("list")
    val symptoms: List<SymptomItem>,
    @SerializedName("copyrighths")
    val copyrighths: String
)


