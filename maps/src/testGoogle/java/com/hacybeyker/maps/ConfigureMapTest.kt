package com.hacybeyker.maps

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.UiSettings
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ConfigureMapTest {

    lateinit var sutConfigureMap: ConfigureMap

    @Mock
    lateinit var mockMapView: MapView

    @Mock
    lateinit var mockGoogleMap: GoogleMap

    @Mock
    lateinit var onDragCompleteListener: OnDragCompleteListener

    @Mock
    lateinit var onMapReadyListener: OnMapReadyListener

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sutConfigureMap = ConfigureMap()
    }

    @Test
    fun verifyMapView() {
        sutConfigureMap.mapView = mockMapView
        val mapView = sutConfigureMap.mapView
        Assert.assertEquals(mapView, mapView)
    }

    @Test
    fun verifyListenerOnDragComplete() {
        sutConfigureMap.listenerOnDragComplete = onDragCompleteListener
        Assert.assertNotNull(sutConfigureMap.listenerOnDragComplete)
    }

    @Test
    fun verifyListenerOnMapReady() {
        sutConfigureMap.listenerOnMapReady = onMapReadyListener
        Assert.assertNotNull(sutConfigureMap.listenerOnMapReady)
    }

    @Test
    fun verifySetZoomControlsEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isZoomControlsEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setZoomControlsEnabled(true)
        val state = mockGoogleMap.uiSettings.isZoomControlsEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetScrollGesturesEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isScrollGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setScrollGesturesEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isScrollGesturesEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetZoomGesturesEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isZoomGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setZoomGesturesEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isZoomGesturesEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetCompassEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isCompassEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setCompassEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isCompassEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetMapToolbarEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isMapToolbarEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setMapToolbarEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isMapToolbarEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetRotateGestureEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isRotateGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setRotateGestureEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isRotateGesturesEnabled
        Assert.assertEquals(state, true)
    }
}