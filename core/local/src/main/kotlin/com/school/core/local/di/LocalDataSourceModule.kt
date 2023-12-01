package com.school.core.local.di

import com.school.core.data.local.LocalAuthDataSource
import com.school.core.local.datasource.LocalAuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun bindsLocalAuthDataSource(
        localAuthDataSourceImpl: LocalAuthDataSourceImpl,
    ): LocalAuthDataSource
}