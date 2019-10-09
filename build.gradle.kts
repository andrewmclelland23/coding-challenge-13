import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.sfeatherstone:geodesy_jvm:v0.2.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("com.github.sfeatherstone:geodesy_jvm:v0.2.0")
    testImplementation("io.mockk:mockk:1.9.3")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}