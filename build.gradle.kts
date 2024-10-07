import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    kotlin("jvm") version "2.0.0"
    id("com.gradleup.shadow") version "8.3.2"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.3.0"
}

group = "io.github.YOURNAME"
version = "MODIFIED"

repositories {
    mavenCentral()
    // Jitpack for Slimefun
    maven("https://jitpack.io/")
    // Paper's repo
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Add all the important Kotlin dependencies
    // The last 2 aren't strictly needed unless you are using sf4k
    paperLibrary(kotlin("stdlib"))
    paperLibrary("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
    paperLibrary(kotlin("reflect"))

    // Paper API and Slimefun
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:e02a0f61d1")

    // sf4k is my own library that acts as a wrapper around Slimefun
    // It's not strictly necessary, but it makes things a lot nicer
    implementation("io.github.seggan:sf4k:0.7.2")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        javaParameters = true
        // sf4k requires this flag for a feature to work properly
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    mergeServiceFiles()

    // bstats requires relocation
    relocate("org.bstats", "io.github.YOURNAME.YOURADDON.bstats")

    // For some reason, Gradle isn't smart enough to not shade these in,
    // so we have to manually exclude them
    dependencies {
        exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8"))
        exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk7"))
        exclude(dependency("org.jetbrains.kotlin:kotlin-stdlib-common"))
        exclude(dependency("org.jetbrains.kotlin:kotlin-reflect"))
        exclude(dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core"))
    }

    archiveBaseName = rootProject.name
}

// This plugin generates the paper-plugin.yml file for us
paper {
    name = rootProject.name
    main = "io.github.YOURNAME.YOURADDON.YourAddon"
    // This loader automatically loads the dependencies specified as paperLibrary
    loader = "io.github.seggan.sf4k.PluginYmlLoader"
    bootstrapper = "io.github.YOURNAME.YOURADDON.YourAddonBootstrapper"
    version = project.version.toString()
    author = "YOURNAME"
    apiVersion = "1.20"
    generateLibrariesJson = true

    serverDependencies {
        register("Slimefun") {
            required = true
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
            joinClasspath = true
        }
    }
}

// This plugin allows us to run a paper server from Gradle. Your addon
// will be automatically added
tasks.runServer {
    // Allows you to add any extra plugins you need
    downloadPlugins {
        url("https://blob.build/dl/Slimefun4/Dev/1156")
        // I like having SlimeHUD on my testing servers, but you can remove this
        url("https://blob.build/dl/SlimeHUD/Dev/3")
    }
    // Change this if needed
    maxHeapSize = "4G"
    minecraftVersion("1.20.6")
}