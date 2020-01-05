package com.arun.journal.repo

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arun.journal.data.JournalResponse
import com.arun.journal.network.ApiError
import com.arun.journal.network.ApiSuccess
import com.arun.journal.network.Urls
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JournalsRemoteRepoTest {

    @Test
    fun getJournalsApi_success_test() {
        val response = runBlocking {
            JournalsRemoteRepo.getJournals()
        }
        assertTrue(response is ApiSuccess<JournalResponse>)
    }

    /**
     * Before test error case, have to set wrong value for [Urls.JOURNAL_LIST]
     * or have to Turn off internet
     * */
    /*@Test
    fun getJournalsApi_error_test() {
        val response = runBlocking {
            JournalsRemoteRepo.getJournals()
        }
        assertTrue(response is ApiError)
    }*/
}