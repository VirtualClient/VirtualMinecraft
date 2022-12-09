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

val fabricApiVersions = mapOf(
    11605 to "0.42.0+1.16",
    11701 to "0.46.1+1.17",
    11802 to "0.59.1+1.18.2",
    11900 to "0.57.0+1.19",
    11902 to "0.64.0+1.19.2",
    11903 to "0.68.1+1.19.3",
)

dependencies {
    api(kotlin("stdlib"))

    api("net.kyori:adventure-api:4.10.1")
    api("net.kyori:adventure-text-serializer-gson:4.10.1")
    api("net.kyori:adventure-text-serializer-legacy:4.10.1")

    setOf(
        "fabric-api-base"
    ).forEach {
        // Add each module as a dependency
        modImplementation(fabricApi.module(it, fabricApiVersions[platform.mcVersion]!!))
    }
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
            version = "1.0.2-${platform.mcVersion}-SNAPSHOT"
            from(components["java"])
            artifact(tasks.jar)
        }
    }
}
