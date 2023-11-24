buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven {
            setUrl("https://developer.huawei.com/repo/")
            isAllowInsecureProtocol = true
            name = "Huawei"
        }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
        classpath("org.jacoco:org.jacoco.core:0.8.7")
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:3.3")
        classpath("com.huawei.agconnect:agcp:1.5.2.300")
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }

    allprojects {
        repositories {
            google()
            mavenCentral()
            mavenLocal()
            maven {
                setUrl("https://developer.huawei.com/repo/")
                isAllowInsecureProtocol = true
                name = "Huawei"
            }
        }
    }
}