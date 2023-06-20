plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinSerialization)
    `maven-publish`
}

group = "com.github.kitakkun"
version = "0.0.2"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverterGson)
    implementation(libs.retrofitConverterScalars)
    implementation(libs.serialization)
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.koin)
    testImplementation(libs.koinTest) {
        // FYI: https://github.com/InsertKoinIO/koin/issues/1526
        exclude(group = "junit", module = "junit")
        exclude("org.jetbrains.kotlin", "kotlin-test-junit")

    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
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
