package com.arun.journal.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arun.journal.R
import com.arun.journal.base.BaseFragment
import com.arun.journal.base.BaseViewModel
import com.arun.journal.data.Journal
import com.arun.journal.data.JournalResponse
import com.arun.journal.databinding.FragmentHomeBinding
import com.arun.journal.network.ApiError
import com.arun.journal.network.ApiSuccess
import com.arun.journal.ui.adapter.JournalsAdapter
import com.arun.journal.ui.adapter.OnJournalClickListener
import com.arun.journal.util.InternetUtil
import com.arun.journal.util.NavigationHelper

/**
 * Fragment for Home screen
 * */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {
    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var journalsAdapter: JournalsAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, activity?.application?.let {
            HomeViewModel.HomeViewModelFactory(it)
        }).get(HomeViewModel::class.java)
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun getDataBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding {
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        return dataBinding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.swipeContainer.setOnRefreshListener(this)
        journalsAdapter = JournalsAdapter(object : OnJournalClickListener {
            override fun onJournalClick(journal: Journal) {
                NavigationHelper.navigateToJournalDetail(findNavController(), journal)
            }
        })
        dataBinding.recycler.adapter = journalsAdapter
        setObserver()
        checkNetworkAndLoadData()
    }
    
    override fun onRefresh() {
        checkNetworkAndLoadData()
    }

    private fun checkNetworkAndLoadData() {
        if (InternetUtil.isInternetOn()) {
            dataBinding.swipeContainer.isRefreshing = true
            dataBinding.tvState.text = getString(R.string.loading)
            viewModel.loadJournals()
        } else {
            dataBinding.swipeContainer.isRefreshing = false
            dataBinding.tvState.text = getString(R.string.no_internet)
        }
    }

    private fun setObserver() {
        viewModel.journalsLiveData.observe(this, Observer {
            it.getContentIfNotHandled()?.let { result ->
                dataBinding.swipeContainer.isRefreshing = false
                when (result) {
                    is ApiSuccess<JournalResponse> -> {
                        dataBinding.tvState.text = result.data.title
                        journalsAdapter.setData(result.data.rows)
                        if (result.data.rows.isEmpty()){
                            dataBinding.tvState.text = getString(R.string.no_data)
                            dataBinding.tvState.visibility = View.VISIBLE
                            dataBinding.recycler.visibility = View.GONE
                        }else{
                            dataBinding.tvState.visibility = View.GONE
                            dataBinding.recycler.visibility = View.VISIBLE
                        }
                    }

                    is ApiError -> {
                        dataBinding.tvState.text = getString(R.string.api_error)
                        dataBinding.tvState.visibility = View.VISIBLE
                        dataBinding.recycler.visibility = View.GONE
                    }
                }
            }
        })
    }
}
