package com.hacybeyker.maps

enum class ZoomLevel(val value: Float) {
    WORLD(WORLD_VALUE),
    CONTINENT(CONTINENT_VALUE),
    CITY(CITY_VALUE),
    STREETS(STREETS_VALUE),
    BUILDINGS(BUILDINGS_VALUE)
}

const val WORLD_VALUE = 1f
const val CONTINENT_VALUE = 5f
const val CITY_VALUE = 10f
const val STREETS_VALUE = 15f
const val BUILDINGS_VALUE = 20f
