package com.fridayhouse.mimblu_assignment.utils

import androidx.recyclerview.widget.DiffUtil
import com.fridayhouse.mimblu_assignment.data.SymptomItem


class SymptomItemDiffCallback(
    private val oldList: List<SymptomItem>,
    private val newList: List<SymptomItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}