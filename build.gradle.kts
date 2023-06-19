plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.github.kitakkun.ktvox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterGson)
    implementation(libs.serialization)
    implementation(libs.coroutinesTest)
    implementation(libs.koin)
    testImplementation(libs.koinTest) {
        // FYI: https://github.com/InsertKoinIO/koin/issues/1526
        exclude(group = "junit", module = "junit")
        exclude("org.jetbrains.kotlin", "kotlin-test-junit")

    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}