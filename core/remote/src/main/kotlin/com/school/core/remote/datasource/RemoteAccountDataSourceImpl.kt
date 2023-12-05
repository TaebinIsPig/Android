package com.school.core.remote.datasource

import com.school.core.data.remote.datasource.RemoteAccountDataSource
import com.school.core.remote.api.AccountAPI
import javax.inject.Inject

class RemoteAccountDataSourceImpl @Inject constructor(
    private val accountAPI: AccountAPI,
) : RemoteAccountDataSource {
}