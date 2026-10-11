// Village-Roles: classic Werewolf roles, registered into Village-Core
plugins {
    id("com.azuredoom.hytale-tools")
}

dependencies {
    // Provided at runtime by the Village-Core jar: compile against it without bundling it
    compileOnly(project(":village-core"))
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

    modId = "Village-Roles"
    mainClass = "dev.gevail.village.roles.VillageRolesPlugin"
    modDescription = "Classic Werewolf roles for Village (Werewolf, Seer, Witch, Hunter...)."
    manifestDependencies = "Hytale:AssetModule=*,Gevail:Village-Core=*"
    includesPack = false
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("Village-Roles")
}
