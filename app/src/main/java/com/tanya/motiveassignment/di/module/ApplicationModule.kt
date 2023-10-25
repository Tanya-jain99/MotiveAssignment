package com.tanya.motiveassignment.di.module

import android.content.Context
import com.tanya.motiveassignment.NewsApplication
import com.tanya.motiveassignment.data.api.NetworkService
import com.tanya.motiveassignment.di.ApplicationContext
import com.tanya.motiveassignment.di.BaseUrl
import com.tanya.motiveassignment.ui.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://secure.geonames.org/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun createHttpClient() : OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        return builder.build()
    }

    @Provides
    @Singleton
    fun createDispatcher() : DispatcherProviderImpl = DispatcherProviderImpl()
}