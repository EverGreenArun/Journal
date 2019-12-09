package com.arun.journal.ui

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.arun.journal.R
import com.arun.journal.base.BaseActivity
import com.arun.journal.databinding.ActivityMainBinding



/**
 * Main screen for application,
 * Holds all fragments and navigation control
 * */
class MainActivity : BaseActivity<ActivityMainBinding>(){

    lateinit var binding: ActivityMainBinding

    override fun getContentView(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getDataBinding() as ActivityMainBinding
        setNavController()
    }

    private fun setNavController() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
    }
}
