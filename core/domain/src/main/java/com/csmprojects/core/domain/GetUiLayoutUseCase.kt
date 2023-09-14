package com.csmprojects.core.domain

import com.csmprojects.core.common.LayoutStructure
import com.csmprojects.core.data.repository.UiLayoutRepository
import com.csmprojects.core.data.repository.WeatherDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUiLayoutUseCase @Inject constructor(
    private val uiLayoutRepository: UiLayoutRepository
) {
    operator fun invoke():LayoutStructure{
        return uiLayoutRepository.getUiLayout()
    }
}