import org.gradle.kotlin.dsl.accessors.runtime.addConfiguredDependencyTo

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    id("com.android.library")
    id("de.jensklingenberg.ktorfit") version "1.0.0"
    id("org.kodein.mock.mockmp") version "1.15.0"
    `maven-publish`
}

group = "com.github.kitakkun"
version = "0.0.3"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(8)

    js(IR) {
        this.nodejs()
        binaries.executable() // not applicable to BOTH, see details below
    }
    jvm()
    androidTarget()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ktvox"
            isStatic = true
        }
    }

    macosX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktorfit.lib)
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
            }
        }
        val commonTest by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.coroutinesTest)
                implementation(libs.koin)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosArm64Main.dependsOn(this)
            iosX64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val jvmMain by getting {
            dependsOn(commonMain)
        }
        val jvmTest by getting {
            dependsOn(jvmMain)
            dependencies {
                implementation(libs.testContainers)
            }
            addConfiguredDependencyTo(
                dependencies,
                implementationConfigurationName,
                libs.koinTest
            ) {
                // FYI: https://github.com/InsertKoinIO/koin/issues/1526
                exclude(group = "junit", module = "junit")
                exclude(group = "org.jetbrains.kotlin", module = "kotlin-test-junit")
            }
        }
        val jsMain by getting
    }
}

val ktorfitVersion = "1.6.0"
dependencies {
    with("de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion") {
        add("kspCommonMainMetadata", this)
        add("kspJvm", this)
        add("kspJvmTest", this)
        add("kspAndroid", this)
        add("kspAndroidTest", this)
        add("kspIosX64", this)
        add("kspIosX64Test", this)
        add("kspIosArm64", this)
        add("kspIosArm64Test", this)
        add("kspIosSimulatorArm64", this)
        add("kspIosSimulatorArm64Test", this)
        add("kspMacosX64", this)
        add("kspMacosX64Test", this)
        add("kspJs", this)
        add("kspJsTest", this)
    }
}

ktorfit {
    version = "1.6.0"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.kitakkun"
            artifactId = "kt-vox"
            version = version

            from(components["kotlin"])
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        targetSdk = 33
        minSdk = 21
    }
    namespace = "com.github.kitakkun.ktvox"
}

mockmp {
    usesHelper = true
}
