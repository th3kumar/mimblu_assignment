package com.fridayhouse.mimblu_assignment.adapter

// SymptomAdapter.kt

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fridayhouse.mimblu_assignment.R
import com.fridayhouse.mimblu_assignment.databinding.ItemSymptomBinding
import com.fridayhouse.mimblu_assignment.data.SymptomItem
import com.fridayhouse.mimblu_assignment.utils.SymptomItemDiffCallback
import com.fridayhouse.mimblu_assignment.viewModel.SymptomViewModel

// SymptomAdapter.kt

class SymptomAdapter(private val viewModel: SymptomViewModel) :
    RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder>() {

    private var symptoms = emptyList<SymptomItem>()


    private var isComputingLayout = false
    private var pendingItems: List<SymptomItem>? = null

    fun setItems(newSymptoms: List<SymptomItem>) {
        // Disable updates during layout computation
        if (isComputingLayout) {
            // If layout computation is in progress, postpone the update
            pendingItems = newSymptoms
        } else {
            val diffResult = DiffUtil.calculateDiff(SymptomItemDiffCallback(symptoms, newSymptoms))
            symptoms = newSymptoms
            diffResult.dispatchUpdatesTo(this)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomViewHolder {
        // Inflate the layout using data binding
        val binding = ItemSymptomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SymptomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        val symptom = symptoms[position]
        holder.bind(symptom)
        // Log statement to check if the symptom is bound to the view holder
        Log.d("SymptomAdapter", "Symptom bound: ${symptom.title}, isSelected: ${symptom.isSelected}")
    }

    override fun getItemCount(): Int {
        return symptoms.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                // RecyclerView is currently computing layout or updating items
                isComputingLayout = true
            }

            override fun onChildViewDetachedFromWindow(view: View) {
                // RecyclerView has completed layout computation and item updates
                isComputingLayout = false
                // If there are pending items, update the adapter now
                pendingItems?.let {
                    setItems(it)
                    pendingItems = null
                }
            }
        })
    }

    inner class SymptomViewHolder(private val binding: ItemSymptomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(symptom: SymptomItem) {
            binding.textViewSymptomName.text = symptom.title
            binding.checkboxSymptom.isChecked = symptom.isSelected

            binding.checkboxSymptom.setOnCheckedChangeListener { _, isChecked ->

                // Check if RecyclerView is computing a layout or scrolling
                if (!isComputingLayout) {
                    viewModel.updateSymptomSelection(symptom.id, isChecked)
                    viewModel.updateButtonState(symptoms)
                }

            }
        }
    }

}

