import org.gradle.jvm.tasks.Jar

plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
}

allprojects {
    group = "me.djtheredstoner"
    version = "0.3"

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"

        options.encoding = "UTF-8"
        // ForgeGradle... ForgeGradle... ForgeGradle...
        if(!this::class.java.name.contains("HackyJavaCompile")) {
            options.release.set(8)
        }
    }

    tasks.withType<Jar> {
        archiveBaseName.set("${rootProject.name}-${project.name}")
    }

    tasks.withType<ProcessResources> {
        from(rootProject.file("LICENSE")) {
            rename { "LICENSE_DevAuth.txt" }
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }

        from(rootProject.file("branding/logo128x.png")) {
            rename { "logo.png" }
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }
    }
}

subprojects {
    apply(plugin = "java-library")
}