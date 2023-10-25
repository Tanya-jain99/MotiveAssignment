package com.tanya.motiveassignment.di.component

import android.content.Context
import com.tanya.motiveassignment.NewsApplication
import com.tanya.motiveassignment.data.api.NetworkService
import com.tanya.motiveassignment.data.repository.Repository
import com.tanya.motiveassignment.di.ApplicationContext
import com.tanya.motiveassignment.di.module.ApplicationModule
import com.tanya.motiveassignment.ui.DispatcherProviderImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getRepository(): Repository

    fun getDispatcherProvider() : DispatcherProviderImpl

}