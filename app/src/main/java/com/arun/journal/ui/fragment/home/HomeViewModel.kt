package com.arun.journal.ui.fragment.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arun.journal.base.BaseViewModel
import com.arun.journal.data.Event
import com.arun.journal.data.JournalResponse
import com.arun.journal.network.ApiResult
import com.arun.journal.repo.JournalsRemoteRepo
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : BaseViewModel(application) {
    val journalsLiveData = MutableLiveData<Event<ApiResult<JournalResponse>>>()

    fun loadJournals(){
        uiScope.launch {
            val result = withContext(bgScope.coroutineContext) {
                JournalsRemoteRepo.getJournals()
            }

            journalsLiveData.value = Event(result)

        }
    }

    class HomeViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java).newInstance(application)
        }
    }
}
