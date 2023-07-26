package com.fridayhouse.mimblu_assignment.data

import com.google.gson.annotations.SerializedName

data class MatchOptionsApiResponse(
    @SerializedName("list")
    val matchOptions: List<MatchOption>,
    @SerializedName("copyrighths")
    val copyrighths: String
)

