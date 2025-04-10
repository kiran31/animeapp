package com.kiran.animeapp.di.component

import android.content.Context
import com.kiran.animeapp.AnimApplication
import com.kiran.animeapp.data.api.NetworkService
import com.kiran.animeapp.data.repository.MainRepository
import com.kiran.animeapp.di.ApplicationContext
import com.kiran.animeapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: AnimApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getMainRepository(): MainRepository
}