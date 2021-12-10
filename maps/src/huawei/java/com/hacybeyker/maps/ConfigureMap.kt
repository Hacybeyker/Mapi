package com.hacybeyker.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.HuaweiMapOptions
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.BitmapDescriptor
import com.huawei.hms.maps.model.BitmapDescriptorFactory
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MapStyleOptions
import com.huawei.hms.maps.model.Marker
import com.huawei.hms.maps.model.MarkerOptions

class ConfigureMap :
    MapManager,
    OnMapReadyCallback,
    HuaweiMap.OnMarkerDragListener,
    OnLifeCycleMap {

    lateinit var mapView: MapView @VisibleForTesting set
    var map: HuaweiMap? = null @VisibleForTesting set
    var listenerOnDragComplete: OnDragCompleteListener? = null @VisibleForTesting set
    var listenerOnMapReady: OnMapReadyListener? = null @VisibleForTesting set
    lateinit var context: Context

    override fun setupMap(context: Context, savedInstanceState: Bundle?) {
        this.context = context
        val options = HuaweiMapOptions().apply {
            compassEnabled(true)
            zoomControlsEnabled(true)
            scrollGesturesEnabled(true)
            zoomGesturesEnabled(true)
            mapToolbarEnabled(true)
        }
        var bundle: Bundle? = null
        if (savedInstanceState != null) {
            bundle = savedInstanceState.getBundle("MAPVIEW_BUNDLE_KEY")
        }
        mapView = MapView(context, options).apply {
            onCreate(bundle)
            getMapAsync(this@ConfigureMap)
        }
    }

    override fun getMapView(): View {
        return mapView
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
        this.map?.uiSettings?.isZoomControlsEnabled = state
    }

    override fun setZoomGesturesEnabled(state: Boolean) {
        this.map?.uiSettings?.isZoomControlsEnabled = state
    }

    override fun setCompassEnabled(state: Boolean) {
        this.map?.uiSettings?.isZoomControlsEnabled = state
    }

    override fun setMapToolbarEnabled(state: Boolean) {
        this.map?.uiSettings?.isMapToolbarEnabled = state
    }

    override fun onMapReady(map: HuaweiMap?) {
        map?.let {
            this.map = it
            this.map?.mapType = HuaweiMap.MAP_TYPE_NORMAL
            setMapStyle(context, R.raw.map_style)
            this.listenerOnMapReady?.onMapReady()
        }
    }

    override fun onMarkerDragStart(marker: Marker) {
    }

    override fun onMarkerDrag(marker: Marker) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        val position = marker.position
        this.listenerOnDragComplete?.onDragComplete(
            CoordinatesVO(
                latitude = position.latitude,
                longitude = position.longitude,
                description = marker.title
            )
        )
        marker.snippet = position.latitude.toString()
        map?.animateCamera(CameraUpdateFactory.newLatLng(position))
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
