package com.arun.journal.ui.fragment.journaldetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arun.journal.base.BaseViewModel

class JournalDetailViewModel(application: Application) : BaseViewModel(application) {

    /**
     * Factory for [JournalDetailViewModel] class
     */
    class JournalDetailViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java).newInstance(application)
        }
    }
}