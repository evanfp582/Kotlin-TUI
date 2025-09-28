plugins {
    kotlin("jvm") version "2.2.0"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("jline:jline:2.14.6")
}

application {
    // Default entry point for the app
    mainClass.set("MainKt")
}

// ---- Fat jar task for Main.kt ----
tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Assemble a runnable fat JAR for Main.kt."
    archiveBaseName.set("Kotlin-TUI")
    archiveVersion.set("")
    archiveClassifier.set("")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith(".jar") }
            .map { zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest { attributes["Main-Class"] = "MainKt" }
}

// ---- Fat jar task for PlaygroundMain.kt ----
tasks.register<Jar>("playgroundJar") {
    group = "build"
    description = "Assemble a runnable fat JAR for PlaygroundMain.kt."
    archiveBaseName.set("Kotlin-TUI-Playground")
    archiveVersion.set("")
    archiveClassifier.set("")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith(".jar") }
            .map { zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest { attributes["Main-Class"] = "PlaygroundMainKt" }
}

// Ensure build also produces both fat jars
tasks.named("build") {
    dependsOn("fatJar", "playgroundJar")
}

// Run helper for Main
tasks.register<Exec>("tuiRun") {
    group = "application"
    description = "Build fat JAR and run Main.kt without Gradle noise."
    dependsOn("fatJar")
    commandLine(
        "java", "-jar",
        "${layout.buildDirectory.get().asFile}/libs/Kotlin-TUI.jar"
    )
    standardInput = System.`in`
    isIgnoreExitValue = true
}

// Run helper for PlaygroundMain
tasks.register<Exec>("playgroundRun") {
    group = "application"
    description = "Build fat JAR and run PlaygroundMain.kt without Gradle noise."
    dependsOn("playgroundJar")
    commandLine(
        "java", "-jar",
        "${layout.buildDirectory.get().asFile}/libs/Kotlin-TUI-Playground.jar"
    )
    standardInput = System.`in`
    isIgnoreExitValue = true
}
