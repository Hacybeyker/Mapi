package com.hacybeyker.maps

import android.content.Context
import android.os.Bundle
import android.view.View
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.HuaweiMapOptions
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.Marker
import com.huawei.hms.maps.model.MarkerOptions

class MapManager : IMapManager, OnMapReadyCallback, HuaweiMap.OnMarkerDragListener {

    private lateinit var map: HuaweiMap
    private lateinit var mapView: MapView
    private lateinit var onMapReadyListener: OnMapReadyListener
    private var onDragCompleteListener: OnDragCompleteListener? = null

    override fun configMap(context: Context, savedInstanceState: Bundle?) {
        val options = HuaweiMapOptions().apply {
            compassEnabled(true)
            zoomControlsEnabled(true)
            scrollGesturesEnabled(true)
            zoomGesturesEnabled(true)
        }
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle =
                savedInstanceState.getBundle("MAPVIEW_BUNDLE_KEY")
        }
        mapView = MapView(context, options).apply {
            onCreate(mapViewBundle)
            getMapAsync(this@MapManager)
        }
    }

    override fun fetchMapView(): View {
        return mapView
    }

    override fun setOnDragCompleteListener(listener: OnDragCompleteListener) {
        this.onDragCompleteListener = listener
    }

    override fun setOnMapReadyListener(listener: OnMapReadyListener) {
        this.onMapReadyListener = listener
    }

    override fun setPositionWithMarker(coordinatesVO: CoordinatesVO, zoomLevel: ZoomLevel) {
        this.map.let { huaweiMap ->
            val place = LatLng(coordinatesVO.latitude, coordinatesVO.longitude)
            huaweiMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, zoomLevel.value))
            huaweiMap.addMarker(MarkerOptions().position(place))
        }
    }

    override fun setZoomControlsEnabled(state: Boolean) {
    }

    override fun setScrollGesturesEnabled(state: Boolean) {
    }

    override fun setZoomGesturesEnabled(state: Boolean) {
    }

    override fun setCompassEnabled(state: Boolean) {
    }

    override fun onMapReady(map: HuaweiMap?) {
        map?.let {
            this.map = it
            this.map.setOnMarkerDragListener(this)
            this.onMapReadyListener.onMapReady()
        }
    }

    override fun onMarkerDragStart(p0: Marker?) {
    }

    override fun onMarkerDrag(p0: Marker?) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        val position = marker.position
        this.onDragCompleteListener?.onDragComplete(
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
