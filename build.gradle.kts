plugins {
    kotlin("jvm") version "1.8.21"
}

group = "com.github.kitakkun.ktvox"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}