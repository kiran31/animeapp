package com.kiran.animeapp

import android.app.Application
import com.kiran.animeapp.di.component.ApplicationComponent
import com.kiran.animeapp.di.component.DaggerApplicationComponent
import com.kiran.animeapp.di.module.ApplicationModule

class AnimApplication : Application() {
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