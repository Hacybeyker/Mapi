package com.hacybeyker.maps

interface MapSetting {
    fun setZoomControlsEnabled(state: Boolean)
    fun setScrollGesturesEnabled(state: Boolean)
    fun setZoomGesturesEnabled(state: Boolean)
    fun setCompassEnabled(state: Boolean)
    fun setMapToolbarEnabled(state: Boolean)
    fun setRotateGestureEnabled(state: Boolean)
}
