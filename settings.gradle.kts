pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.library:com.android.library.gradle.plugin:8.1.1")
    }
}

rootProject.name = "kt-vox"
include(":kt-vox")
