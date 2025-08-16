import org.gradle.kotlin.dsl.release
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    id("maven-publish")
}

android {
    namespace = "sco.carlukesoftware.extensions"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xcontext-sensitive-resolution")
            jvmTarget = JvmTarget.fromTarget("21")
        }
    }

    sourceSets["main"].java {
        srcDirs(
            "src/main/kotlin",
            "src/common/kotlin",
            "src/debug/kotlin",
            "src/release/kotlin",
            "src/staging/kotlin",
            "src/preproduction/kotlin"
        )
    }

    // Add this to your android block to generate Javadoc from Kotlin sources
    // (if you are using Dokka, the setup is different)
    // For basic Javadoc with KDoc:
    tasks.withType<Javadoc> {
        source(android.sourceSets["main"].java.srcDirs)
        classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
        // Exclude generated files if necessary
        exclude("**/R.class", "**/BuildConfig.class")
    }
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.annotation)
}

// Add these tasks to create the sources and Javadoc JARs
// Put these at the root level of your build.gradle.kts, outside publishing block
tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
    from(android.sourceSets["main"].kotlin.srcDirs()) // Include Kotlin sources
}

//tasks.register<Jar>("javadocJar") {
//    archiveClassifier.set("javadoc")
//    from(tasks.named("javadoc")) // Depends on the standard javadoc task
//}


publishing {
    publications {
        // Inside your MavenPublication block:
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
            groupId = "com.github.forteanjo"
            artifactId = "extensions"
            version = "1.0.0"

            // Add these for sources and Javadoc
            artifact(tasks.named("sourcesJar")) // Assumes you have a sourcesJar task (see below)
//            artifact(tasks.named("javadocJar")) // Assumes you have a javadocJar task (see below)

            pom { /* ... */ }
        }
    }
}
