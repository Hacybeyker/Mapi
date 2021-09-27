package com.hacybeyker.maps

interface OnLifeCycleMap {
    fun onStart()
    fun onStop()
    fun onDestroy()
    fun onPause()
    fun onResume()
    fun onLowMemory()
}