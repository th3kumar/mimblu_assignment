package com.fridayhouse.mimblu_assignment.utils

import androidx.recyclerview.widget.DiffUtil
import com.fridayhouse.mimblu_assignment.data.MatchOption

class MatchOptionDiffUtil(
    private val oldList: List<MatchOption>,
    private val newList: List<MatchOption>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMatchOption = oldList[oldItemPosition]
        val newMatchOption = newList[newItemPosition]
        return oldMatchOption == newMatchOption
    }
}