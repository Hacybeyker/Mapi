package com.hacybeyker.maps

/*@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)*/
class MapManagerTest {
/*
    lateinit var sutConfigureMap: IMapManager

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
        MockitoAnnotations.initMocks(this)
        sutConfigureMap = IMapManager()
    }

    @Test
    fun verifyMapView() {
        sutConfigureMap.mapView = mockMapView
        val mapView = sutConfigureMap.mapView
        Assert.assertEquals(mapView, mapView)
    }

    @Test
    fun verifyListenerOnDragComplete() {
        sutConfigureMap.listenerDragComplete = onDragCompleteListener
        Assert.assertNotNull(sutConfigureMap.listenerDragComplete)
    }

    @Test
    fun verifyListenerOnMapReady() {
        sutConfigureMap.listenerMapReady = onMapReadyListener
        Assert.assertNotNull(sutConfigureMap.listenerMapReady)
    }

    @Test
    fun verifySetZoomControlsEnabled() {
        val mockUISettings = mock(UiSettings::class.java)
        `when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        `when`(mockUISettings.isZoomControlsEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setZoomControlsEnabled(true)
        val state = mockGoogleMap.uiSettings.isZoomControlsEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetScrollGesturesEnabled() {
        val mockUISettings = mock(UiSettings::class.java)
        `when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        `when`(mockUISettings.isScrollGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setScrollGesturesEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isScrollGesturesEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetZoomGesturesEnabled() {
        val mockUISettings = mock(UiSettings::class.java)
        `when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        `when`(mockUISettings.isZoomGesturesEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setZoomGesturesEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isZoomGesturesEnabled
        Assert.assertEquals(state, true)
    }

    @Test
    fun verifySetCompassEnabled() {
        val mockUISettings = mock(UiSettings::class.java)
        `when`(mockGoogleMap.uiSettings).thenReturn(mockUISettings)
        `when`(mockUISettings.isCompassEnabled).thenReturn(true)

        sutConfigureMap.map = mockGoogleMap
        sutConfigureMap.setCompassEnabled(true)
        val state = sutConfigureMap.map?.uiSettings?.isCompassEnabled
        Assert.assertEquals(state, true)
    }*/
}
