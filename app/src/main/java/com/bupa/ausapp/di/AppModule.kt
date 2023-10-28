package com.bupa.ausapp.di

import android.content.Context
import com.bupa.ausapp.service.AusCityService
import com.bupa.ausapp.service.CityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCityService(@ApplicationContext context: Context): CityService {
        return AusCityService(context)
    }
}
