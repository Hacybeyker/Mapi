package com.hacybeyker.maps

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hacybeyker.maps.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), OnDragCompleteListener, OnMapReadyListener {

    private lateinit var binding: ActivityMainBinding

    private val mapManager: IMapManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapManager.configMap(this, savedInstanceState)
        mapManager.setOnDragCompleteListener(this)
        mapManager.setOnMapReadyListener(this)
        val map = mapManager.fetchMapView()
        val viewContainer = binding.containerMap
        viewContainer.removeAllViews()
        viewContainer.addView(map)

        binding.tvhw.setOnClickListener {
            Log.d("TAG", "Here - onCreate: Helouda")
        }
    }

    override fun onDragComplete(coordinatesVO: CoordinatesVO) {
        Log.d("TAG", "Here - onDragComplete: ${coordinatesVO.latitude},${coordinatesVO.longitude}")
    }

    override fun onMapReady() {
        Log.d("TAG", "Here - onMapReady: ")
        mapManager.setPositionWithMarker(
            CoordinatesVO(
                -33.415425476237196,
                -70.59023828953504,
                "Edificio EuroAmerica"
            ), ZoomLevel.STREETS
        )
    }

    override fun onStart() {
        super.onStart()
        mapManager.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapManager.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapManager.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapManager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapManager.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapManager.onLowMemory()
    }
}