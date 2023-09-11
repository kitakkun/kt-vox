plugins {
    kotlin("multiplatform") version libs.versions.kotlin
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
    id("com.android.library")
    `maven-publish`
}

group = "com.github.kitakkun"
version = "0.1.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(8)

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
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
            }
        }
        val commonTest by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.coroutinesTest)
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
        }
    }
}

val ktorfitVersion = "1.6.0"

dependencies {
    with("de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion") {
        add("kspCommonMainMetadata", this)
        add("kspJvm", this)
        add("kspAndroid", this)
        add("kspIosX64", this)
        add("kspIosArm64", this)
        add("kspIosSimulatorArm64", this)
        add("kspMacosX64", this)
    }
}

ktorfit {
    version = ktorfitVersion
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
