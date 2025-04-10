package com.kiran.animeapp.di.component

import com.kiran.animeapp.di.ActivityScope
import com.kiran.animeapp.di.module.ActivityModule
import com.kiran.animeapp.ui.animedetails.AnimeDetailActivity
import com.kiran.animeapp.ui.topanime.TopAnimeActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: TopAnimeActivity)
    fun inject(activity: AnimeDetailActivity)
}