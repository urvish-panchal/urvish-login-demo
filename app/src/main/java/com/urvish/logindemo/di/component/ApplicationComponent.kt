package com.urvish.logindemo.di.component

import android.app.Application
import com.urvish.logindemo.LoginDemoApplication
import com.urvish.logindemo.di.module.ActivityModule
import com.urvish.logindemo.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: LoginDemoApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}