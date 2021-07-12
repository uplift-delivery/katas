import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_test_version: String by project
val exposed_version: String by project
val postgresql_version: String by project
val hikari_version: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

group = "com.uplift_delivery.advanced_gilded_rose"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_test_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")

    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.postgresql:postgresql:$postgresql_version")
    implementation("com.zaxxer:HikariCP:$hikari_version")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("com.uplift_delivery.advanced_gilded_rose.ApplicationKt")
}