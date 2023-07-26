package com.fridayhouse.mimblu_assignment.data



// MatchOption.kt
data class MatchOption(
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,
    val video_description: String,
    val final_price: String // Update the property name to final_price
)

