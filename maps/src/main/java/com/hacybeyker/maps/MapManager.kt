package com.hacybeyker.maps

import android.content.Context
import android.os.Bundle
import android.view.View

interface MapManager {
    fun setupMap(context: Context, savedInstanceState: Bundle? = null)
    fun getMapView(): View
    fun setOnDragCompleteListener(listener: OnDragCompleteListener)
    fun setOnMapReadyListener(listener: OnMapReadyListener)
    fun setPositionWithMarker(coordinatesVO: CoordinatesVO, zoomLevel: ZoomLevel = ZoomLevel.STREETS, iconRes: Int = R.drawable.iconmaps)
    fun setZoomControlsEnabled(state: Boolean)
    fun setScrollGesturesEnabled(state: Boolean)
    fun setZoomGesturesEnabled(state: Boolean)
    fun setCompassEnabled(state: Boolean)
    fun setMapToolbarEnabled(state: Boolean)
}
