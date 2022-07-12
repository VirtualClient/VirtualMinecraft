plugins {
    `java-library`
    kotlin("jvm")
    id("gg.essential.multi-version")
    id("gg.essential.defaults")
    id("maven-publish")
}

group = "gg.virtualclient"

repositories {
    maven {
        url = uri("https://repo.virtualclient.gg/artifactory/virtualclient-public/")
    }
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib:1.5.10")

    implementation("net.kyori:adventure-api:4.10.1")
    implementation("net.kyori:adventure-text-serializer-gson:4.10.1")
    implementation("gg.virtualclient:VirtualEvents:1.0-SNAPSHOT")

}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "virtualclientRepository"
            credentials(PasswordCredentials::class)
            url = uri("https://repo.virtualclient.gg/artifactory/virtualclient/")
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
