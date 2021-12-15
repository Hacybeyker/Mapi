package com.hacybeyker.maps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hacybeyker.maps.databinding.ActivityMainBinding
import com.hacybeyker.maps.view.FragmentMap

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentMap: FragmentMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupMap()
    }

    private fun setupMap() {
        fragmentMap = FragmentMap.newInstance(
            coordinatesVO = CoordinatesVO(
                latitude = -33.4410298,
                longitude = -70.6511565,
                description = "Falabella Electrohogar"
            ),
            propertyMapVO = PropertyMapVO(
                zoomControlsEnabled = true,
                scrollGesturesEnabled = true,
                zoomGesturesEnabled = true,
                compassEnabled = true,
                mapToolbarEnabled = true,
                rotateGestureEnabled = true,
                zoomLevel = ZoomLevel.STREETS,
                iconMarker = R.drawable.iconmaps_red
            )
        )
        supportFragmentManager.beginTransaction().add(R.id.fcvShowMap, fragmentMap).commit()
    }
}