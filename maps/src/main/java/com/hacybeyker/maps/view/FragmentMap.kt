package com.hacybeyker.maps.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hacybeyker.maps.ConfigureMap
import com.hacybeyker.maps.CoordinatesVO
import com.hacybeyker.maps.OnMapReadyListener
import com.hacybeyker.maps.PropertyMapVO
import com.hacybeyker.maps.R
import com.hacybeyker.maps.ZoomLevel
import com.hacybeyker.maps.databinding.FragmentMapBinding

class FragmentMap : Fragment(), OnMapReadyListener {

    private lateinit var binding: FragmentMapBinding
    private val configureMap: ConfigureMap by lazy { ConfigureMap() }
    private lateinit var coordinatesVO: CoordinatesVO
    private var propertyMapVO: PropertyMapVO? = null

    companion object {
        @JvmStatic
        fun newInstance(
            coordinatesVO: CoordinatesVO,
            propertyMapVO: PropertyMapVO? = null
        ): FragmentMap {
            return FragmentMap().apply {
                arguments = Bundle().apply {
                    putParcelable(CoordinatesVO::class.java.name, coordinatesVO)
                    putParcelable(PropertyMapVO::class.java.name, propertyMapVO)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureMap.setupMap(requireContext(), savedInstanceState)
        configureMap.setOnMapReadyListener(this)
        showMap()
    }

    private fun showMap() {
        val mapView = configureMap.getMapView()
        binding.fcvContainerMap.removeAllViews()
        binding.fcvContainerMap.addView(mapView)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let { coordinatesVOArgument ->
            coordinatesVOArgument.getParcelable<CoordinatesVO>(CoordinatesVO::class.java.name)
                ?.let { this.coordinatesVO = it }
            coordinatesVOArgument.getParcelable<PropertyMapVO>(PropertyMapVO::class.java.name)
                ?.let { propertyMapVOArgument -> this.propertyMapVO = propertyMapVOArgument }
        }
    }

    override fun onMapReady() {
        loadMapSetting()
        configureMap.setPositionWithMarker(
            coordinatesVO = CoordinatesVO(
                latitude = coordinatesVO.latitude,
                longitude = coordinatesVO.longitude,
                description = coordinatesVO.description
            ),
            zoomLevel = propertyMapVO?.zoomLevel ?: ZoomLevel.STREETS,
            iconRes = propertyMapVO?.iconMarker ?: R.drawable.iconmaps
        )
    }

    private fun loadMapSetting() {
        configureMap.setZoomControlsEnabled(propertyMapVO?.zoomControlsEnabled ?: false)
        configureMap.setScrollGesturesEnabled(propertyMapVO?.scrollGesturesEnabled ?: false)
        configureMap.setZoomGesturesEnabled(propertyMapVO?.zoomGesturesEnabled ?: false)
        configureMap.setCompassEnabled(propertyMapVO?.compassEnabled ?: false)
        configureMap.setMapToolbarEnabled(propertyMapVO?.mapToolbarEnabled ?: false)
        configureMap.setRotateGestureEnabled(propertyMapVO?.rotateGestureEnabled ?: false)
    }

    fun zoomControlsEnabled(state: Boolean) {
        configureMap.setZoomControlsEnabled(state = state)
    }

    fun scrollGesturesEnabled(state: Boolean) {
        configureMap.setScrollGesturesEnabled(state = state)
    }

    fun zoomGesturesEnabled(state: Boolean) {
        configureMap.setZoomGesturesEnabled(state = state)
    }

    fun compassEnabled(state: Boolean) {
        configureMap.setCompassEnabled(state = state)
    }

    fun mapToolbarEnabled(state: Boolean) {
        configureMap.setMapToolbarEnabled(state = state)
    }

    fun rotateGestureEnabled(state: Boolean) {
        configureMap.setRotateGestureEnabled(state = state)
    }

    override fun onStart() {
        super.onStart()
        configureMap.onStart()
    }

    override fun onResume() {
        super.onResume()
        configureMap.onResume()
    }

    override fun onStop() {
        super.onStop()
        configureMap.onStop()
    }

    override fun onPause() {
        super.onPause()
        configureMap.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        configureMap.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        configureMap.onLowMemory()
    }
}
