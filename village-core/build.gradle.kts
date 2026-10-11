// Village-Core: game engine (games, phases, players, votes, role registry)
plugins {
    id("com.azuredoom.hytale-tools")
}

hytaleTools {
    // hytaleVersion, patchline and manifestGroup are inherited from the root workspace
    javaVersion = property("java_version").toString().toInt()
    manifestServerVersion = property("manifestServerVersion").toString()
    modCredits = property("mod_author").toString()
    modUrl = property("mod_url").toString()
    injectServerJavadocsIntoSources = property("injectServerJavadocsIntoSources").toString().toBoolean()
    generateAssetsBinary = property("generateAssetsBinary").toString().toBoolean()
    // Local Assets.zip, set in ~/.gradle/gradle.properties (optional)
    findProperty("hytaleHomeOverride")?.let { hytaleHomeOverride = it.toString() }

    modId = "Village-Core"
    mainClass = "dev.gevail.village.core.VillageCorePlugin"
    modDescription = "Village core engine: games, phases, players, votes and role registry."
    manifestDependencies = "Hytale:AssetModule=*"
    includesPack = false
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("Village-Core")
}
