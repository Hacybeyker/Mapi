package com.hacybeyker.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
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
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapManager : IMapManager, OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    lateinit var mapView: MapView @VisibleForTesting set
    lateinit var map: GoogleMap @VisibleForTesting set
    var listenerDragComplete: OnDragCompleteListener? = null @VisibleForTesting set
    var listenerMapReady: OnMapReadyListener? = null @VisibleForTesting set
    private var context: Context? = null

    override fun configMap(context: Context, savedInstanceState: Bundle?) {
        this.context = context
        val options = GoogleMapOptions().apply {
            compassEnabled(true)
            zoomControlsEnabled(true)
            scrollGesturesEnabled(true)
            zoomGesturesEnabled(true)
        }
        mapView = MapView(context, options).apply {
            onCreate(savedInstanceState)
            getMapAsync(this@MapManager)
        }
    }

    override fun fetchMapView(): View {
        return mapView
    }

    override fun onMapReady(map: GoogleMap?) {
        Log.d("TAG", "Here - onMapReady: READYYY!!! ")
        map?.let {
            this.map = it
            this.map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this.context,
                    R.raw.map_style
                )
            )
            this.listenerMapReady?.onMapReady()
        }
    }

    override fun setOnDragCompleteListener(listener: OnDragCompleteListener) {
        this.listenerDragComplete = listener
    }

    override fun setOnMapReadyListener(listener: OnMapReadyListener) {
        this.listenerMapReady = listener
    }

    override fun setPositionWithMarker(coordinatesVO: CoordinatesVO, zoomLevel: ZoomLevel) {
        this.map.let { googleMap ->
            val place = LatLng(coordinatesVO.latitude, coordinatesVO.longitude)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, zoomLevel.value))
            // googleMap.addMarker(MarkerOptions().position(place))
            val markerIcon = bitmapDescriptorFromVector(context!!, R.drawable.iconmaps)
            googleMap.addMarker(MarkerOptions().position(place).icon(markerIcon))
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        return vectorDrawable?.let {
            val width = it.intrinsicWidth
            val height = it.intrinsicHeight
            it.setBounds(0, 0, width, height)
            Log.d("TAG", "Here - bitmapDescriptorFromVector: width: $width ")
            Log.d("TAG", "Here - bitmapDescriptorFromVector: height: $height")
            val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            it.draw(canvas)
            BitmapDescriptorFactory.fromBitmap(bitmap)
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

    override fun onMarkerDragStart(p0: Marker) {
    }

    override fun onMarkerDrag(p0: Marker) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        val position = marker.position
        this.listenerDragComplete?.onDragComplete(
            CoordinatesVO(
                latitude = position.latitude,
                longitude = position.longitude,
                description = marker.title
            )
        )
        marker.snippet = position.latitude.toString()
        map.animateCamera(CameraUpdateFactory.newLatLng(position))
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
}
