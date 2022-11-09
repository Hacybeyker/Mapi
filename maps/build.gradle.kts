plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
}

apply {
    from("uploader.gradle")
}


/*apply {
    from("sonarqube.gradle")
    from("jacoco.gradle")
    from("uploader.gradle")

}*/

android {
    compileSdk = VersionApp.compileSdkVersion
    buildToolsVersion = VersionApp.buildToolsVersion

    defaultConfig {
        minSdk = VersionApp.minSdkVersion
        targetSdk = VersionApp.targetSdkVersion
        testInstrumentationRunner = VersionApp.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
        renderscriptSupportModeEnabled = true
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            resValue(
                "string",
                "google_maps_key",
                project.findProperty("GOOGLE_MAPS_API_KEY").toString()
            )
        }
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            resValue(
                "string",
                "google_maps_key",
                project.findProperty("GOOGLE_MAPS_API_KEY").toString()
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()

    buildFeatures {
        viewBinding = true
    }

    flavorDimensions.add("app")
    productFlavors {
        create("google") {
            dimension = "app"
        }
        create("huawei") {
            dimension = "app"
        }
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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

    lint {
        disable.add("TypographyFractions")
        disable.add("TypographyQuotes")
        checkDependencies = true
        abortOnError = false
        ignoreWarnings = false
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
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.mockitoInline)
    testImplementation(TestDependencies.mockitoKotlin)
    testImplementation(TestDependencies.kotlinCoroutinesTest)
    testImplementation(TestDependencies.robolectric)
    // Koin
    implementation(MainApplicationDependencies.koinCore)
    implementation(MainApplicationDependencies.koinAndroidxScope)
    implementation(MainApplicationDependencies.koinAndroidxViewModel)
    // Detekt
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.18.0")
    // Maps
    "huaweiImplementation"(MainApplicationDependencies.hmsMaps)
    "googleImplementation"(MainApplicationDependencies.gmsMaps)
}

