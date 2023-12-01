package com.school.core.local.di

import com.school.core.local.preference.AuthPreference
import com.school.core.local.preference.AuthPreferenceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
    @Binds
    abstract fun bindsAuthPreference(
        authPreferenceImpl: AuthPreferenceImpl,
    ): AuthPreference
}