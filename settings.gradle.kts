pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
//    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    kotlin("multiplatform") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
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
