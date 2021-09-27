plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.google.secrets_gradle_plugin") version "0.5"
}

//apply(from: "maven-push.gradle")
apply(from = "distribution.gradle.kts")

android {
    compileSdkVersion(VersionApp.compileSdkVersion)
    buildToolsVersion(VersionApp.buildToolsVersion)

    defaultConfig {
        minSdkVersion(VersionApp.minSdkVersion)
        targetSdkVersion(VersionApp.targetSdkVersion)
        versionCode(2)
        versionName(Configuration.versionName)
        testInstrumentationRunner(VersionApp.testInstrumentationRunner)
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            //resValue "string", "google_maps_key", (project.findProperty("GOOGLE_MAPS_API_KEY") ?: "")
            resValue(
                "string",
                "google_maps_key",
                project.findProperty("GOOGLE_MAPS_API_KEY").toString() ?: ""
            )
        }
        /*integration {}
        qa {}*/
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            resValue(
                "string",
                "google_maps_key",
                project.findProperty("GOOGLE_MAPS_API_KEY").toString() ?: ""
            )
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
            print("Here - GMS")
        }
        create("huawei") {
            dimension = "app"
            print("Here - HMS")
        }
    }
/*
    task sourceJar (type: Jar) {
    from android . sourceSets . main . java . srcDirs
            classifier "sources"
}*/
}

dependencies {
    //Kotlin
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
    //Test
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.robolectric)
    //Maps
    "huaweiImplementation"(MainApplicationDependencies.hmsMaps)
    "googleImplementation"(MainApplicationDependencies.gmsMaps)
}