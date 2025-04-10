package com.kiran.animeapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kiran.animeapp.data.repository.MainRepository
import com.kiran.animeapp.di.ActivityContext
import com.kiran.animeapp.ui.animedetails.AnimeDetailViewModel
import com.kiran.animeapp.ui.base.ViewModelProviderFactory
import com.kiran.animeapp.ui.topanime.AnimeListViewModel
import com.kiran.animeapp.ui.topanime.TopAnimeAdapter
import com.kiran.animeapp.utils.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity : AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext() : Context{
        return activity
    }

    @Provides
    fun provideNetworkHelper(): NetworkHelper {
        return NetworkHelper(activity)
    }

    @Provides
    fun provideAnimeListViewModule(mainRepository : MainRepository,networkHelper: NetworkHelper) : AnimeListViewModel {
        return ViewModelProvider(activity , ViewModelProviderFactory(AnimeListViewModel::class){
            AnimeListViewModel(mainRepository,networkHelper)
        })[AnimeListViewModel::class.java]
    }

    @Provides
    fun provideAnimeDetailViewModel(mainRepository : MainRepository,networkHelper : NetworkHelper) : AnimeDetailViewModel {
        return ViewModelProvider(activity , ViewModelProviderFactory(AnimeDetailViewModel::class){
            AnimeDetailViewModel(mainRepository, networkHelper)
        })[AnimeDetailViewModel::class.java]
    }

    @Provides
    fun provideTopAnimeAdapter() = TopAnimeAdapter(ArrayList())
}