package com.fridayhouse.mimblu_assignment.data

// SymptomItem.kt

data class SymptomItem(
    val id: Int,
    val title: String,
    val stateId: Int,
    val createdOn: String?,
    val typeId: Int,
    var isSelected: Boolean,
    val createdById: Int
)

