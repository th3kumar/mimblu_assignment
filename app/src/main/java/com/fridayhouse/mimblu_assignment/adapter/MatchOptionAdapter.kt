package com.fridayhouse.mimblu_assignment.adapter

// MatchOptionAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fridayhouse.mimblu_assignment.R
import com.fridayhouse.mimblu_assignment.data.MatchOption

class MatchOptionAdapter(private val matchOptions: List<MatchOption>) : RecyclerView.Adapter<MatchOptionAdapter.MatchOptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchOptionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_match_option, parent, false)
        return MatchOptionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MatchOptionViewHolder, position: Int) {
        val matchOption = matchOptions[position]
        holder.bind(matchOption)
    }

    override fun getItemCount(): Int {
        return matchOptions.size
    }

    inner class MatchOptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        private val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        private val textViewDuration: TextView = itemView.findViewById(R.id.textViewDuration)
        private val textViewVideoDescription: TextView = itemView.findViewById(R.id.textViewVideoDescription)
        private val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)

        fun bind(matchOption: MatchOption) {
            textViewTitle.text = matchOption.title
            textViewDescription.text = matchOption.description
            textViewDuration.text = "Duration: ${matchOption.duration} days"
            textViewVideoDescription.text = "Video Description: ${matchOption.videoDescription}"
            textViewPrice.text = "Price: ${matchOption.final_price}"
        }
    }
}

