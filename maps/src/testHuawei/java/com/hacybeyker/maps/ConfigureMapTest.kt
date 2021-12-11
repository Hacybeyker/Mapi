package com.hacybeyker.maps

import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.UiSettings
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
    lateinit var mockHuaweiMap: HuaweiMap

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
        Mockito.`when`(mockHuaweiMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isZoomControlsEnabled).thenReturn(true)

        sutConfigureMap.map = mockHuaweiMap
        sutConfigureMap.setZoomControlsEnabled(true)
        val state = mockHuaweiMap.uiSettings.isZoomControlsEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetScrollGesturesEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockHuaweiMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isScrollGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockHuaweiMap
        sutConfigureMap.setScrollGesturesEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isScrollGesturesEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetZoomGesturesEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockHuaweiMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isZoomGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockHuaweiMap
        sutConfigureMap.setZoomGesturesEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isZoomGesturesEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetCompassEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockHuaweiMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isCompassEnabled).thenReturn(true)

        sutConfigureMap.map = mockHuaweiMap
        sutConfigureMap.setCompassEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isCompassEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetMapToolbarEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockHuaweiMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isMapToolbarEnabled).thenReturn(true)

        sutConfigureMap.map = mockHuaweiMap
        sutConfigureMap.setMapToolbarEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isMapToolbarEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetRotateGestureEnabled() {
        val mockUISettings = Mockito.mock(UiSettings::class.java)
        Mockito.`when`(mockHuaweiMap.uiSettings).thenReturn(mockUISettings)
        Mockito.`when`(mockUISettings.isRotateGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockHuaweiMap
        sutConfigureMap.setRotateGestureEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isRotateGesturesEnabled
        Assert.assertEquals(state, true)
    }
}