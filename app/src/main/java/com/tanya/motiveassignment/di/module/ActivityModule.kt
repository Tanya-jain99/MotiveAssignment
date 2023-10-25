package com.tanya.motiveassignment.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.tanya.motiveassignment.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }
}