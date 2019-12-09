package com.arun.journal.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * BaseViewHolder for RecyclerView child
 * */
open class BaseViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun unBind() {
        binding.unbind()
    }
}