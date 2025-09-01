plugins {
    kotlin("jvm") version "2.2.0"
    application
     id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories { mavenCentral() }

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("jline:jline:2.14.6")
}

application {
    mainClass.set("MainKt")
}


// ---- Fat jar task (yours) ----
tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Assemble a runnable fat JAR."
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
    manifest { attributes["Main-Class"] = application.mainClass.get() }
}

// Ensure build also produces fat jar
tasks.named("build") { dependsOn("fatJar") }

// Run helper
tasks.register<Exec>("tuiRun") {
    group = "application"
    description = "Build fat JAR and run it without Gradle noise."
    dependsOn("fatJar")
    commandLine("java", "-jar", "${layout.buildDirectory}/libs/Kotlin-TUI.jar")
    standardInput = System.`in`
    isIgnoreExitValue = true
}
