package com.school.core.domain.usecase.school

import com.school.core.domain.repository.SchoolRepository
import javax.inject.Inject

class SearchSchoolUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository,
) {
    suspend operator fun invoke(schoolName: String) = kotlin.runCatching {
        schoolRepository.searchSchool(schoolName = schoolName)
    }
}