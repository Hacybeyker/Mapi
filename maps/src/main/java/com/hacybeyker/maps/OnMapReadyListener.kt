package com.hacybeyker.maps

interface OnMapReadyListener {
    fun showMap()
    fun addMarker(
        coordinatesVO: CoordinatesVO,
        zoomLevel: ZoomLevel = ZoomLevel.STREETS,
        animateCamera: Boolean = true,
        icon: Int = R.drawable.iconmaps
    )

    fun onMapReady()
}
