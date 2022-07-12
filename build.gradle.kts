plugins {
    kotlin("jvm") version "1.6.10" apply false
    id("gg.essential.multi-version.root")
}

preprocess {
    val version11900 = createNode("1.19-fabric", 11900, "yarn")
    val version11802 = createNode("1.18.2-fabric", 11802, "yarn")
    val version11701 = createNode("1.17.1-fabric", 11701, "yarn")
    val version11605 = createNode("1.16.4-fabric", 11605, "yarn")
    version11900.link(version11802, file("minecraft-versions/1.19.0-1.18.2.txt"))
    version11802.link(version11701, file("minecraft-versions/1.18.2-1.17.1.txt"))
    version11701.link(version11605, file("minecraft-versions/1.17.1-1.16.5.txt"))
}