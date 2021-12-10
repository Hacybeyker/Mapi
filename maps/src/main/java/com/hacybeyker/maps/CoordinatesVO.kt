package com.hacybeyker.maps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoordinatesVO(
    val latitude: Double,
    val longitude: Double,
    val description: String? = ""
) : Parcelable
