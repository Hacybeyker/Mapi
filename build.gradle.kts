buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven {
            setUrl("https://developer.huawei.com/repo/")
            name = "Huawei"
        }
        maven {
            setUrl(ConfigureApp.urlRepoDependencies)
            isAllowInsecureProtocol = true
            credentials {
                username = findProperty("REPO_USERID") as String? ?: System.getenv("REPO_USERID")
                password = findProperty("REPO_TOKEN") as String? ?: System.getenv("REPO_TOKEN")
            }
        }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.huawei.agconnect:agcp:1.5.2.300")
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.0")
    }
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
        maven {
            setUrl(ConfigureApp.urlRepoDependencies)
            isAllowInsecureProtocol = true
            credentials {
                username = findProperty("REPO_USERID") as String? ?: System.getenv("REPO_USERID")
                password = findProperty("REPO_TOKEN") as String? ?: System.getenv("REPO_TOKEN")
            }
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}