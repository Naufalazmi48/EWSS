package com.example.ewss.ui.main.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.HistoryDiagnosa
import com.example.ewss.R
import com.example.ewss.databinding.ItemHistoryBinding
import com.example.ewss.utils.StatusPatient.checkStatusColor

class HistoryAdapter(private val context: Context) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(), Filterable {

    private val data = arrayListOf<HistoryDiagnosa>()
    var onItemClick: ((HistoryDiagnosa) -> Unit)? = null

    fun setData(newList: List<HistoryDiagnosa>) {
        if (!newList.isNullOrEmpty()) {
            val diffCallback = HistoryDiffCallback(data, newList)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            data.clear()
            data.addAll(newList)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemHistoryBinding.bind(itemView)
        fun bind(historyDiagnosa: HistoryDiagnosa) {
            with(binding) {
                name.text = historyDiagnosa.nama
                val diagnosa = historyDiagnosa.diagnosaResult
                if (diagnosa != null) {
                    status.text =
                        context.getString(
                            R.string.status,
                            diagnosa.result
                        )
                    label.setColorFilter(ContextCompat.getColor(context, checkStatusColor(diagnosa.result)))
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(data[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder =
        HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {

                val charString = charSequence.toString()

                var mFilteredList = mutableListOf<HistoryDiagnosa>()
                if (charSequence.isNotEmpty()) {
                    val filteredList = data
                        .filter { it.nama.lowercase().contains(charString) }
                        .toMutableList()

                    mFilteredList = filteredList
                }

                val filterResults = FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                data.clear()
                data.addAll(filterResults.values as MutableList<HistoryDiagnosa>)
                notifyDataSetChanged()
            }
        }
    }


}