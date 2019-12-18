package com.arun.journal.ui.fragment.home

import androidx.test.core.app.ApplicationProvider
import com.arun.journal.data.JournalResponse
import com.arun.journal.network.ApiSuccess
import com.arun.journal.repo.JournalsRemoteRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel

    @Before
    fun initViewModel() {
        viewModel = HomeViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun loadJournalsApi_test() {
        val response = runBlocking {
            JournalsRemoteRepo.getJournals()
        }
        assertTrue(response is ApiSuccess<JournalResponse> )
    }
}