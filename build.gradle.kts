import me.djtheredstoner.devauth.build.fixRemap
import org.gradle.jvm.tasks.Jar

plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
    id("gg.essential.loom") version "0.10.0.2" apply false
    id("java-library")
    id("build-logic")
}

allprojects {
    group = "me.djtheredstoner"
    version = "1.0.0"

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"

        options.encoding = "UTF-8"

        options.release.set(8)
    }

    tasks.withType<Jar> {
        archiveBaseName.set("${rootProject.name}-${project.name}")
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "build-logic")

    java {
        withSourcesJar()
    }
}

fixRemap()