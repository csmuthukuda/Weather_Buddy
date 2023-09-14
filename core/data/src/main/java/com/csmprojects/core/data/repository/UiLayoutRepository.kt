package com.csmprojects.core.data.repository

import com.csmprojects.core.common.LayoutStructure
import kotlinx.coroutines.flow.Flow

interface UiLayoutRepository {
    fun getUiLayout(): LayoutStructure
}