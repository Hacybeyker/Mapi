package com.hacybeyker.maps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PropertyMapVO(
    val zoomControlsEnabled: Boolean? = false,
    val setScrollGesturesEnabled: Boolean? = false,
    val setZoomGesturesEnabled: Boolean? = false,
    val setCompassEnabled: Boolean? = false,
    val setMapToolbarEnabled: Boolean? = false,
    val zoomLevel: ZoomLevel? = ZoomLevel.STREETS,
    val iconMarker: Int? = R.drawable.iconmaps,
) : Parcelable
