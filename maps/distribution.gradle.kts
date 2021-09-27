apply(plugin="maven")
//apply(plugin = "maven-publish")

apply {
    from("uploader.gradle")
}

group = Configuration.groupName
version = generateVersion(Configuration.versionName)

fun isReleaseBranch(): Boolean {
    val branchName = System.getenv("BRANCH_NAME")
    var isReleaseBranch = "master" == branchName
    if (!isReleaseBranch && branchName != null)
        isReleaseBranch = branchName.startsWith("re-")
    return isReleaseBranch
}

fun generateVersion(versionName: String): String {
    val flavor = System.getenv("FLAVOR")
    val branchName = System.getenv("BRANCH_NAME")
    val isDevelopBranch = "develop" == branchName
    if (isReleaseBranch()) {
        return "$versionName-$flavor"
    }
    if (isDevelopBranch) {
        return "$versionName-$flavor-SNAPSHOT"
    }
    return "$versionName-$flavor-$branchName-SNAPSHOT"
}


afterEvaluate {
    tasks.named("assembleGoogleRelease").configure {
        doLast {
            println("HEREEEEEEEE - $buildDir")
            val file = File("$buildDir/outputs/aar/maps-google-release.aar")
            file.renameTo(File("$buildDir/outputs/aar/maps-${generateVersion(Configuration.versionName)}.aar"))
        }
    }
    tasks.named("assembleHuaweiRelease").configure {
        doLast {
            val file = File("$buildDir/outputs/aar/maps-huawei-release.aar")
            file.renameTo(File("$buildDir/outputs/aar/maps-${generateVersion(Configuration.versionName)}.aar"))
        }
    }
}

tasks.register("printVersion") {
    doLast {
        print(Configuration.versionName)
    }
}
