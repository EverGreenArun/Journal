package com.arun.journal.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Base class for fragments in this application
 * */
abstract class BaseFragment<out V : ViewDataBinding, out T : BaseViewModel> : Fragment() {

    /**
     * View Model variable of fragment layout
     * */
    private lateinit var mViewModel: BaseViewModel

    /**
     * Data binding variable of fragment layout
     * */
    private lateinit var mDataBinding: ViewDataBinding

    /**
     * getter for mViewModel
     * */
    abstract fun getViewModel(): BaseViewModel

    /**
     * getter for mDataBinding
     * */
    abstract fun getDataBinding(inflater: LayoutInflater, container: ViewGroup?): ViewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = getDataBinding(inflater, container)
        return mDataBinding.root
    }
}