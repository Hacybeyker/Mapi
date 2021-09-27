// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://developer.huawei.com/repo/")
            name = "Huawei"
        }
        /*maven {
            url = MAVEN_REPOSITORY_URL_RELEASE
            isAllowInsecureProtocol = true
            credentials {
                username = USERNAME
                password = PASSWORD
            }
        }*/
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.huawei.agconnect:agcp:1.5.2.300")
        classpath("com.google.gms:google-services:4.3.10")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = ("https://developer.huawei.com/repo/"))
        /*maven {
            url = MAVEN_REPOSITORY_URL_RELEASE
            isAllowInsecureProtocol = true
            credentials {
                username = USERNAME
                password = PASSWORD
            }
        }*/
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}