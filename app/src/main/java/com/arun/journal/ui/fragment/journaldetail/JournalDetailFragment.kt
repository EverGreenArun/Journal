package com.arun.journal.ui.fragment.journaldetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.arun.journal.base.BaseBottomSheetFragment
import com.arun.journal.base.BaseViewModel
import com.arun.journal.databinding.FragmentJournalDetailBinding
import com.arun.journal.util.FrescoUtils

class JournalDetailFragment :
    BaseBottomSheetFragment<FragmentJournalDetailBinding, JournalDetailViewModel>() {
    private val args: JournalDetailFragmentArgs by navArgs()
    private lateinit var dataBinding: FragmentJournalDetailBinding
    private val viewModel by lazy {
        ViewModelProviders.of(
            this,
            activity?.application?.let {
                JournalDetailViewModel.JournalDetailViewModelFactory(
                    it
                )
            })
            .get(JournalDetailViewModel::class.java)
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getDataBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding {
        dataBinding = FragmentJournalDetailBinding
            .inflate(inflater, container, false)
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        return dataBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.journal = args.journal
        dataBinding.executePendingBindings()
        args.journal.imageHref?.let {
            FrescoUtils.setFresco(it, dataBinding.ivImage)
        }
    }
}