pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()        // Repository untuk dependensi Android
        mavenCentral()  // Repository untuk dependensi umum
        maven { url = uri("https://jitpack.io") } // Repository JitPack untuk library eksternal
    }
}

rootProject.name = "KabupatenKotaRiauApp"
include(":app")
