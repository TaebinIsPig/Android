package com.school.core.data.di

import com.school.core.data.repository.AccountRepositoryImpl
import com.school.core.data.repository.AuthRepositoryImpl
import com.school.core.data.repository.SchoolRepositoryImpl
import com.school.core.domain.repository.AccountRepository
import com.school.core.domain.repository.AuthRepository
import com.school.core.domain.repository.SchoolRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    abstract fun bindsAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl,
    ): AccountRepository

    @Binds
    abstract fun bindsSchoolRepository(
        schoolRepositoryImpl: SchoolRepositoryImpl,
    ): SchoolRepository
}