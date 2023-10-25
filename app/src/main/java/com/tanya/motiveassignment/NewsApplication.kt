package com.tanya.motiveassignment

import android.app.Application
import com.tanya.motiveassignment.di.component.ApplicationComponent
import com.tanya.motiveassignment.di.component.DaggerApplicationComponent
import com.tanya.motiveassignment.di.module.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}