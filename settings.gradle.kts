pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net")
        maven("https://maven.architectury.dev/")
        maven("https://maven.minecraftforge.net")
        maven("https://repo.virtualclient.gg/artifactory/virtualclient-public/")
        maven("https://repo.essential.gg/repository/maven-public")
    }
    plugins {
        id("gg.virtualclient.multi-version.root") version "0.1.16"
    }
}

rootProject.name = "VirtualMinecraft"

listOf(
    "1.19.3-fabric",
    "1.19-fabric",
    "1.18.2-fabric",
    "1.17.1-fabric",
    "1.16.5-fabric",
    ).forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("minecraft-versions/$version")
        buildFileName = "../version.gradle.kts"
    }
}