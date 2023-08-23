plugins {
    kotlin("jvm") version "1.9.0"
}

group = "com.echo-youn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jasypt:jasypt:1.9.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}