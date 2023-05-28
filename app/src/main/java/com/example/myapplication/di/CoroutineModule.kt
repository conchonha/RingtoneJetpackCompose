package com.example.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {
    @IOCoroutineScope
    @Provides
    fun provideIOCoroutineScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO+ CoroutineExceptionHandler{_,_->

    })

    @MainCoroutineScope
    @Provides
    fun provideMainCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main + CoroutineExceptionHandler { _, throwable ->
            //no handle exception/loading dialog
        })
}