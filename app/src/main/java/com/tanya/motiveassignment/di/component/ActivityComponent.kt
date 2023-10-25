package com.tanya.motiveassignment.di.component

import com.tanya.motiveassignment.di.ActivityScope
import com.tanya.motiveassignment.di.module.ActivityModule
import com.tanya.motiveassignment.ui.base.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}