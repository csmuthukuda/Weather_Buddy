package com.csmprojects.core.data.repository

import com.csmprojects.core.common.LayoutItem
import com.csmprojects.core.common.LayoutStructure
import javax.inject.Inject

class DefaultUiLayoutRepository @Inject constructor(

) : UiLayoutRepository {
    override fun getUiLayout(): LayoutStructure {
        //mimicking the UI layout API response
        //this should be get through retrofit response and map to LayoutStructure data class
        //JSON structure
//        {
//            "top_left": {
//            "visibility": true,
//            "component": "temperature"
//        },
//            "top_right": {
//            "visibility": true,
//            "component": "weathercode"
//        },
//            "middle_left": {
//            "visibility": true,
//            "component": "windspeed"
//        },
//            "middle_right": {
//            "visibility": true,
//            "component": "general_info"
//        },
//            "bottom_left": {
//            "visibility": true,
//            "component": "hourly_weather_summary"
//        },
//            "bottom_right": {
//            "visibility": true,
//            "component": "daily_weather_summary"
//        }
//        }
        val mockResponse = LayoutStructure(
            top_left = LayoutItem(true, "daily_weather_summary"),
            top_right = LayoutItem(true, "weathercode"),
            middle_left = LayoutItem(true, "windspeed"),
            middle_right = LayoutItem(true, "general_info"),
            bottom_left = LayoutItem(true, "hourly_weather_summary"),
            bottom_right = LayoutItem(true, "temperature")
        )
        return mockResponse
    }

}