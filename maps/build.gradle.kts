plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("com.google.secrets_gradle_plugin") version "0.5"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("io.gitlab.arturbosch.detekt").version("1.18.1")
}

apply {
    from("uploader.gradle")
}

android {
    compileSdk = VersionApp.compileSdkVersion
    buildToolsVersion = VersionApp.buildToolsVersion

    defaultConfig {
        minSdk = VersionApp.minSdkVersion
        targetSdk = VersionApp.targetSdkVersion
        testInstrumentationRunner = VersionApp.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            // resValue "string", "google_maps_key", (project.findProperty("GOOGLE_MAPS_API_KEY") ?: "")
            resValue(
                "string",
                "google_maps_key",
                project.findProperty("GOOGLE_MAPS_API_KEY").toString() ?: ""
            )
        }
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
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = "1.8"
    }

    flavorDimensions.add("app")
    productFlavors {
        create("google") {
            dimension = "app"
            println("Here - Maps - GMS")
        }
        create("huawei") {
            dimension = "app"
            println("Here - Maps - HMS")
        }
    }

    detekt {
        buildUponDefaultConfig = true
        allRules = true
        config = files("$projectDir/config/detekt.yml")
        reports {
            html.enabled = true
            xml.enabled = true
            txt.enabled = false
            sarif.enabled = false
        }
    }
}

dependencies {
    // Kotlin
    implementation(MainApplicationDependencies.coreKtx)
    // View
    implementation(MainApplicationDependencies.appCompat)
    implementation(MainApplicationDependencies.material)
    implementation(MainApplicationDependencies.constraintLayout)
    // Test
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.extJUnit)
    androidTestImplementation(TestDependencies.espressoCore)
    // Koin
    implementation(MainApplicationDependencies.koinCore)
    implementation(MainApplicationDependencies.koinAndroidxScope)
    implementation(MainApplicationDependencies.koinAndroidxViewModel)
    // Test
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.robolectric)
    // Maps
    "huaweiImplementation"(MainApplicationDependencies.hmsMaps)
    "googleImplementation"(MainApplicationDependencies.gmsMaps)
}
