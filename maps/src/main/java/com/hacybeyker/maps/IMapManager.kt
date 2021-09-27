package com.hacybeyker.maps

import android.content.Context
import android.os.Bundle
import android.view.View

interface IMapManager {
    fun configMap(context: Context, savedInstanceState: Bundle? = null)
    fun fetchMapView(): View
    fun setOnDragCompleteListener(listener: OnDragCompleteListener)
    fun setOnMapReadyListener(listener: OnMapReadyListener)
    fun setPositionWithMarker(
        coordinatesVO: CoordinatesVO,
        zoomLevel: ZoomLevel = ZoomLevel.STREETS
    )

    fun setZoomControlsEnabled(state: Boolean)
    fun setScrollGesturesEnabled(state: Boolean)
    fun setZoomGesturesEnabled(state: Boolean)
    fun setCompassEnabled(state: Boolean)

    fun onStart()
    fun onStop()
    fun onDestroy()
    fun onPause()
    fun onResume()
    fun onLowMemory()
}