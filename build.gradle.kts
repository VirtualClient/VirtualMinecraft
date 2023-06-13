
plugins {
    kotlin("jvm") version "1.6.10" apply false
    id("gg.virtualclient.multi-version.root")
}

preprocess {
    val version12000 = createNode("1.20.1-fabric", 12001, "yarn")
    val version11904 = createNode("1.19.4-fabric", 11904, "yarn")
    val version11903 = createNode("1.19.3-fabric", 11903, "yarn")
    val version11900 = createNode("1.19-fabric", 11900, "yarn")
    val version11802 = createNode("1.18.2-fabric", 11802, "yarn")
    val version11701 = createNode("1.17.1-fabric", 11701, "yarn")
    val version11605 = createNode("1.16.5-fabric", 11605, "yarn")
    version12000.link(version11904)
    version11904.link(version11903)
    version11903.link(version11900, file("minecraft-versions/1.19.3-1.19.0.txt"))
    version11900.link(version11802, file("minecraft-versions/1.19.0-1.18.2.txt"))
    version11802.link(version11701, file("minecraft-versions/1.18.2-1.17.1.txt"))
    version11701.link(version11605, file("minecraft-versions/1.17.1-1.16.5.txt"))
}