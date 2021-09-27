plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.secrets_gradle_plugin") version "0.5"
}

android {
    compileSdkVersion(VersionApp.compileSdkVersion)
    buildToolsVersion(VersionApp.buildToolsVersion)

    defaultConfig {
        applicationId("com.hacybeyker.maps")
        minSdkVersion(VersionApp.minSdkVersion)
        targetSdkVersion(VersionApp.targetSdkVersion)
        versionCode(2)
        versionName(Configuration.versionName)
        testInstrumentationRunner(VersionApp.testInstrumentationRunner)
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        /*integration {}
        qa {}*/
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

    flavorDimensions("app")
    productFlavors {
        create("google") {
            dimension = "app"
            //setDimension("app")
            //buildConfigField "String", "SERVICE_USED", '"g"'
            //applicationIdSuffix ".google"
            print("Here - GMS")
        }
        create("huawei") {
            dimension = "app"
            //setDimension("app")
            //apply(plugin = "com.huawei.agconnect")
            //buildConfigField "String", "SERVICE_USED", '"h"'
            print("Here - HMS")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //Libs
    implementation(fileTree("libs") { include(listOf("*.jar", "*.aar")) })
    //kotlin
    implementation(MainApplicationDependencies.kotlinStdlib)
    implementation(MainApplicationDependencies.coreKtx)
    //View
    implementation(MainApplicationDependencies.appCompat)
    implementation(MainApplicationDependencies.material)
    implementation(MainApplicationDependencies.constraintLayout)
    //Test
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.extJUnit)
    androidTestImplementation(TestDependencies.espressoCore)
    //Koin
    implementation(MainApplicationDependencies.koinCore)
    implementation(MainApplicationDependencies.koinAndroidxScope)
    implementation(MainApplicationDependencies.koinAndroidxViewModel)
    //Maps
    "googleImplementation"(project(path = ":maps"))
    "huaweiImplementation"(project(path = ":maps"))

    //implementation files('libs/maps.aar')
    //implementation("com.hacybeyker.maps:maps:1.0.0")
}