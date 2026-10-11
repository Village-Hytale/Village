// Root project: orchestrates the Village modules (no code here)
plugins {
    id("com.azuredoom.hytale-workspace") version "1.+"
    id("com.azuredoom.hytale-tools") version "1.+" apply false
}

hytaleWorkspace {
    modProjects = listOf(":village-core")
    hostProject = ":village-core"
    // Shared defaults inherited by every module's hytaleTools block
    manifestGroup = property("manifest_group").toString()
    hytaleVersion = property("hytale_version").toString()
    patchline = property("patchline").toString()
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    extensions.configure<JavaPluginExtension> {
        toolchain.languageVersion.set(JavaLanguageVersion.of(property("java_version").toString().toInt()))
    }

    tasks.withType<Javadoc>().configureEach {
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:-missing", "-quiet")
    }
}
