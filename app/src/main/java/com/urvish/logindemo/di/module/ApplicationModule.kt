package com.urvish.logindemo.di.module

import android.app.Application
import android.content.Context
import com.urvish.logindemo.data.remote.login.LoginDataRepository
import com.urvish.logindemo.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideLoginRepository(loginDataRepository: LoginDataRepository): LoginRepository {
        return loginDataRepository;
    }
}