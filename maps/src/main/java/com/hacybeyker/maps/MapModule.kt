package com.hacybeyker.maps

import org.koin.dsl.module

val mapModule = module {
    single<IMapManager> { MapManager() }
}