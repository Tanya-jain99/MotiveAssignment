package com.tanya.motiveassignment.di.component

import com.tanya.motiveassignment.di.FragmentScope
import com.tanya.motiveassignment.di.module.SearchModule
import com.tanya.motiveassignment.ui.search.SearchFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class] , modules = [SearchModule::class])
interface SearchComponent{
    fun injectDependencies(fragment : SearchFragment)
}