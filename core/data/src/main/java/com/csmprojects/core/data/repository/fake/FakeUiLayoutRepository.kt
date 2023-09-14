package com.csmprojects.core.data.repository.fake

import com.csmprojects.core.common.LayoutItem
import com.csmprojects.core.common.LayoutStructure
import com.csmprojects.core.data.repository.UiLayoutRepository

class FakeUiLayoutRepository : UiLayoutRepository {
    override fun getUiLayout(): LayoutStructure {

        return getMockUiLayout()
    }

    fun getMockUiLayout(): LayoutStructure {
        return LayoutStructure(
            top_left = LayoutItem(true, "daily_weather_summary"),
            top_right = LayoutItem(true, "weathercode"),
            middle_left = LayoutItem(true, "windspeed"),
            middle_right = LayoutItem(true, "general_info"),
            bottom_left = LayoutItem(true, "hourly_weather_summary"),
            bottom_right = LayoutItem(true, "temperature")
        )
    }
}