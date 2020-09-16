package com.urvish.logindemo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.urvish.logindemo.di.key.ViewModelKey
import com.urvish.logindemo.presentation.base.ViewModelFactory
import com.urvish.logindemo.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModule(loginViewModel: LoginViewModel): ViewModel
}