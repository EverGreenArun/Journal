package com.arun.journal.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * BaseViewModel class for the application
 * */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = SupervisorJob()

    /**
     * execute on main thread
     * */
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * execute on background thread
     * */
    protected val bgScope = CoroutineScope(Dispatchers.IO + viewModelJob)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}