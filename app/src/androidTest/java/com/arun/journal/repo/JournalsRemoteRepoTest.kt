package com.arun.journal.repo

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arun.journal.data.JournalResponse
import com.arun.journal.network.ApiSuccess
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JournalsRemoteRepoTest{

    @Test
    fun getJournalsApi_test() {
        val response = runBlocking {
            JournalsRemoteRepo.getJournals()
        }
        assertTrue(response is ApiSuccess<JournalResponse> )
    }
}