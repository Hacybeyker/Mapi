plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.secrets_gradle_plugin") version "0.5"
}

android {
    compileSdk = VersionApp.compileSdkVersion
    buildToolsVersion = VersionApp.buildToolsVersion

    defaultConfig {
        applicationId = "com.hacybeyker.maps"
        minSdk = VersionApp.minSdkVersion
        targetSdk = VersionApp.targetSdkVersion
        versionCode = 2
        versionName = ConfigureApp.versionName
        testInstrumentationRunner = VersionApp.testInstrumentationRunner
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
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

    flavorDimensions.add("app")
    productFlavors {
        create("google") {
            dimension = "app"
            applicationId = "com.hacybeyker.app_android_maps"
            println("Here - Demo - GMS")
        }
        create("huawei") {
            dimension = "app"
            applicationId = "com.hacybeyker.app_android_maps"
            apply(plugin = "com.huawei.agconnect")
            println("Here - Demo - HMS")
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
}