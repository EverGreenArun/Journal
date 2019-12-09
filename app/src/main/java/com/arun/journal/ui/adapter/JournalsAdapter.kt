package com.arun.journal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arun.journal.base.BaseViewHolder
import com.arun.journal.data.Journal
import com.arun.journal.databinding.ViewHolderJournalBinding
import com.arun.journal.util.FrescoUtils

/**
 * @property onJournalClickListener listener
 */
class JournalsAdapter(private val onJournalClickListener: OnJournalClickListener) :
    RecyclerView.Adapter<JournalsAdapter.JournalItemViewHolder>() {
    private var journals: List<Journal> = emptyList()

    /**
     * @return [JournalItemViewHolder] object
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalItemViewHolder {
        return JournalItemViewHolder(
            ViewHolderJournalBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onJournalClickListener
        )
    }

    /**
     * Change data in adapter
     * @param newData the data to change
     */
    fun setData(newData: List<Journal>) {
        if (newData !== journals) {
            journals = newData
            notifyDataSetChanged()
        }
    }

    /**
     * @return size of data set
     */
    override fun getItemCount(): Int = journals.size

    override fun onBindViewHolder(holder: JournalItemViewHolder, position: Int) {
        holder.onBind(journals[position])
    }

    /**
     * ViewHolder class for Journal
     */
    inner class JournalItemViewHolder(
        private val binding: ViewHolderJournalBinding,
        private val onJournalClickListener: OnJournalClickListener
    ) : BaseViewHolder(binding) {
        fun onBind(journal: Journal) {
            binding.journal = journal
            binding.container.setOnClickListener {
                onJournalClickListener.onJournalClick(journal)
            }
            journal.imageHref?.let {
                FrescoUtils.setFresco(it, binding.ivImage)
            }
            binding.executePendingBindings()
        }
    }
}

interface OnJournalClickListener {
    fun onJournalClick(journal: Journal)
}