package com.arun.journal.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Base class for Activity screens in this application
 * */
abstract class BaseActivity<out V : ViewDataBinding> : AppCompatActivity() {

    /**
     * Data binding variable of activity layout
     * */
    private lateinit var dataBinding: ViewDataBinding

    /**
     * returns activity's layout file
     * */
    abstract fun getContentView(): Int

    /**
     * Should call after onCreate()
     * returns DataBinding of activity
     * */
    fun getDataBinding(): ViewDataBinding = dataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getContentView())
    }
}