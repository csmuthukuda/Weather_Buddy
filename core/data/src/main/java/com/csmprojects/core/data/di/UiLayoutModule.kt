package com.csmprojects.core.data.di

import com.csmprojects.core.data.repository.DefaultUiLayoutRepository
import com.csmprojects.core.data.repository.UiLayoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UiLayoutModule {
    @Binds
    fun bindsUiLayoutRepository(
        uiLayoutRepository: DefaultUiLayoutRepository
    ): UiLayoutRepository
}