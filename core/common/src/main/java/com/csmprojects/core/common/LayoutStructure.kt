package com.csmprojects.core.common


data class LayoutStructure(
    val top_left: LayoutItem,
    val top_right: LayoutItem,
    val middle_left: LayoutItem,
    val middle_right: LayoutItem,
    val bottom_left: LayoutItem,
    val bottom_right: LayoutItem
)

data class LayoutItem(
    val visibility: Boolean,
    val component: String
)
