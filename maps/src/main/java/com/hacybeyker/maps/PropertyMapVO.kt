package com.hacybeyker.maps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PropertyMapVO(
    val zoomControlsEnabled: Boolean? = false,
    val scrollGesturesEnabled: Boolean? = false,
    val zoomGesturesEnabled: Boolean? = false,
    val compassEnabled: Boolean? = false,
    val mapToolbarEnabled: Boolean? = false,
    val rotateGestureEnabled: Boolean? = false,
    val zoomLevel: ZoomLevel? = ZoomLevel.STREETS,
    val iconMarker: Int? = R.drawable.iconmaps,
) : Parcelable
