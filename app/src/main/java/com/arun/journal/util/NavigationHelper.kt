package com.arun.journal.util

import android.os.Bundle
import androidx.navigation.NavController
import com.arun.journal.R
import com.arun.journal.data.Journal

/**
 * Helper class for screen navigation
 * */
object NavigationHelper {
    //argument keys
    private const val JOURNAL = "journal"

    fun navigateToJournalDetail(navController: NavController, journal: Journal) {
        val result = Bundle()
        result.putParcelable(JOURNAL, journal)
        with(navController) {
            navigate(R.id.action_global_journal_detail, result)
        }
    }
}