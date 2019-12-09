package com.arun.journal.network

import com.arun.journal.data.JournalResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET(Urls.JOURNAL_LIST)
    fun getJournalsAsync(): Deferred<Response<JournalResponse>>
}