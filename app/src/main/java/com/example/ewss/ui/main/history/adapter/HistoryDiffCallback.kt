package com.example.ewss.ui.main.history.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.model.HistoryDiagnosa

class HistoryDiffCallback(
    private val oldList: List<HistoryDiagnosa>,
    private val newList: List<HistoryDiagnosa>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldHistoryDiagnosa = oldList[oldItemPosition]
        val newHistoryDiagnosa = newList[newItemPosition]

        return ((oldHistoryDiagnosa.nama == newHistoryDiagnosa.nama) && (oldHistoryDiagnosa.alamat == newHistoryDiagnosa.alamat))
    }
}