plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.secrets_gradle_plugin") version "0.5"
}

android {
    namespace = "com.hacybeyker.maps.demo"
    compileSdk = VersionApp.compileSdkVersion

    defaultConfig {
        applicationId = "com.hacybeyker.maps.demo"
        minSdk = VersionApp.minSdkVersion
        targetSdk = VersionApp.targetSdkVersion
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = VersionApp.testInstrumentationRunner
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()

    buildFeatures {
        viewBinding = true
    }

    flavorDimensions.add("app")
    productFlavors {
        create("google") {
            dimension = "app"
            applicationId = "com.hacybeyker.maps.demo"
        }
        create("huawei") {
            dimension = "app"
            applicationId = "com.hacybeyker.maps.demo"
            apply(plugin = "com.huawei.agconnect")
        }
    }

    testOptions{
        unitTests {
            isIncludeAndroidResources = true
        }
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

    //Maps
    "googleImplementation"(project(path = ":maps"))
    "huaweiImplementation"(project(path = ":maps"))
}