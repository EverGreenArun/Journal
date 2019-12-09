package com.arun.journal.repo

import com.arun.journal.data.JournalResponse
import com.arun.journal.network.ApiFactory
import com.arun.journal.network.ApiResult
import com.arun.journal.network.ResultHelper

object JournalsRemoteRepo {
    suspend fun getJournals(): ApiResult<JournalResponse> {
        return try {
            val response =
                ApiFactory.makeRetrofitService().getJournalsAsync().await()
            ResultHelper.handleResult(response)
        } catch (e: Exception) {
            ResultHelper.getDefaultError()
        }
    }
}