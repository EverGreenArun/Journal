package com.arun.journal.ui.fragment.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arun.journal.data.JournalResponse
import com.arun.journal.network.Api
import com.arun.journal.network.ApiFactory
import com.arun.journal.network.ApiSuccess
import com.arun.journal.network.ResultHelper
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    private lateinit var retrofitClient: Api

    @Before
    fun init() {
        retrofitClient = ApiFactory.makeRetrofitService()
    }

    @Test
    fun loadJournals() {
        var isSuccess = false
        val response = runBlocking {
            retrofitClient.getJournalsAsync().await()
        }
        when (ResultHelper.handleResult(response)) {
            is ApiSuccess<JournalResponse> -> {
                isSuccess = true
            }
        }
        assertTrue(isSuccess)
    }
}