package com.school.core.remote.di

import com.school.core.data.remote.datasource.RemoteAccountDataSource
import com.school.core.data.remote.datasource.RemoteAuthDateSource
import com.school.core.data.remote.datasource.RemoteSchoolDataSource
import com.school.core.remote.datasource.RemoteAccountDataSourceImpl
import com.school.core.remote.datasource.RemoteAuthDataSourceImpl
import com.school.core.remote.datasource.RemoteSchoolDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindsRemoteAuthDataSource(
        remoteAuthDataSourceImpl: RemoteAuthDataSourceImpl,
    ): RemoteAuthDateSource

    @Binds
    abstract fun bindsRemoteAccountDataSource(
        remoteAccountDataSourceImpl: RemoteAccountDataSourceImpl,
    ): RemoteAccountDataSource

    @Binds
    abstract fun bindsRemoteSchoolDataSource(
        remoteSchoolDataSourceImpl: RemoteSchoolDataSourceImpl,
    ): RemoteSchoolDataSource
}