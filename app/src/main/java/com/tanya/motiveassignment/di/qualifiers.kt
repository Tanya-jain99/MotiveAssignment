package com.tanya.motiveassignment.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class BaseUrl