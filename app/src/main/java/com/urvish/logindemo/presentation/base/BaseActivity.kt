package com.urvish.logindemo.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {

    abstract val layout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        configureDagger()
        viewSetup()
        viewModelSetUp()
    }

    abstract fun viewModelSetUp()

    abstract fun viewSetup()

    private fun configureDagger() {
        AndroidInjection.inject(this)
    }
}