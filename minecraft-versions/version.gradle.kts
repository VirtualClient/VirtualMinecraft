import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
    id("gg.virtualclient.multi-version")
    id("gg.virtualclient.defaults")
    id("maven-publish")
}

group = "gg.virtualclient"

repositories {
    maven {
        url = uri("https://repo.virtualclient.gg/artifactory/virtualclient-public/")
    }
}

dependencies {
    api(kotlin("stdlib"))

    api("net.kyori:adventure-api:4.10.1")
    api("net.kyori:adventure-text-serializer-gson:4.10.1")
    api("net.kyori:adventure-text-serializer-legacy:4.10.1")
    implementation("gg.virtualclient:VirtualEvents:1.0.1-SNAPSHOT")

}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "virtualclientRepository"
            credentials(PasswordCredentials::class)
            url = uri("https://repo.virtualclient.gg/artifactory/virtualclient-public/")
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "gg.virtualclient"
            artifactId = "virtualminecraft"
            version = "1.0.0-${platform.mcVersion}-SNAPSHOT"
            from(components["java"])
            artifact(tasks.jar)
        }
    }
}
