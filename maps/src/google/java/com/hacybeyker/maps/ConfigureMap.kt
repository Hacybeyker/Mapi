package com.hacybeyker.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class ConfigureMap : MapManager, OnMapReadyCallback, MapSetting, OnLifeCycleMap {

    lateinit var mapView: MapView @VisibleForTesting set
    var map: GoogleMap? = null @VisibleForTesting set
    var listenerOnDragComplete: OnDragCompleteListener? = null @VisibleForTesting set
    var listenerOnMapReady: OnMapReadyListener? = null @VisibleForTesting set
    lateinit var context: Context

    override fun setupMap(context: Context, savedInstanceState: Bundle?) {
        this.context = context
        val options = GoogleMapOptions().apply {
            compassEnabled(false)
            zoomControlsEnabled(false)
            scrollGesturesEnabled(false)
            zoomGesturesEnabled(false)
            mapToolbarEnabled(false)
            rotateGesturesEnabled(false)
        }
        this.mapView = MapView(context, options).apply {
            onCreate(savedInstanceState)
            getMapAsync(this@ConfigureMap)
        }
    }

    override fun getMapView(): View {
        return this.mapView
    }

    override fun setOnDragCompleteListener(listener: OnDragCompleteListener) {
        this.listenerOnDragComplete = listener
    }

    override fun setOnMapReadyListener(listener: OnMapReadyListener) {
        this.listenerOnMapReady = listener
    }

    override fun setPositionWithMarker(
        coordinatesVO: CoordinatesVO,
        zoomLevel: ZoomLevel,
        iconRes: Int
    ) {
        this.map?.let { map ->
            val place = LatLng(coordinatesVO.latitude, coordinatesVO.longitude)
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(place, zoomLevel.value))
            val markerIcon = bitmapDescriptorFromVector(context, iconRes)
            map.addMarker(MarkerOptions().position(place).icon(markerIcon))
        }
    }

    override fun setZoomControlsEnabled(state: Boolean) {
        this.map?.uiSettings?.isZoomControlsEnabled = state
    }

    override fun setScrollGesturesEnabled(state: Boolean) {
        this.map?.uiSettings?.isScrollGesturesEnabled = state
    }

    override fun setZoomGesturesEnabled(state: Boolean) {
        this.map?.uiSettings?.isZoomGesturesEnabled = state
    }

    override fun setCompassEnabled(state: Boolean) {
        this.map?.uiSettings?.isCompassEnabled = state
    }

    override fun setMapToolbarEnabled(state: Boolean) {
        this.map?.uiSettings?.isMapToolbarEnabled = state
    }

    override fun setRotateGestureEnabled(state: Boolean) {
        this.map?.uiSettings?.isRotateGesturesEnabled = state
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let {
            this.map = it
            this.map?.mapType = GoogleMap.MAP_TYPE_NORMAL
            setMapStyle(context, R.raw.map_style)
            this.listenerOnMapReady?.onMapReady()
        }
    }

    override fun onStart() {
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
    }

    override fun onPause() {
        mapView.onPause()
    }

    override fun onResume() {
        mapView.onResume()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
    }

    private fun setMapStyle(context: Context, resource: Int) {
        this.map?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, resource))
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        return vectorDrawable?.let {
            val width = it.intrinsicWidth
            val height = it.intrinsicHeight
            it.setBounds(0, 0, width, height)
            val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            it.draw(canvas)
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }
}
