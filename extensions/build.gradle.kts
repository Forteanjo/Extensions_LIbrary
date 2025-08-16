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
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xcontext-sensitive-resolution")
            jvmTarget = JvmTarget.fromTarget(libs.versions.jvmTarget.get())
        }
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

    publishing {
        singleVariant("release") {
            // This will automatically create a publication component for the "release" variant
            withSourcesJar() // This is good, it tells AGP to include sources
            // withJavadocJar() // Uncomment if you have a Javadoc JAR task and want to include it
        }

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

afterEvaluate {
    publishing {
        publications {
            // Configure the "release" publication that AGP's `singleVariant("release")` creates.
            // The name of the publication created by AGP for singleVariant("release")
            // is conventionally the name of the variant itself, so "release".
            // However, it's safer to configure all MavenPublications of type MavenPublication if you only have one.
            // Or, if you know the name is 'release' (which it should be for singleVariant("release")).

            // Option A: Configure the specific publication if you are sure of its name
            // (AGP creates one named after the variant, so "release" in this case)
            // AGP creates a publication named after the variant, so "release" in this case.
            create<MavenPublication>("release") {
                // The `from(components["release"])` is often handled automatically by singleVariant,
                // but explicitly adding it inside afterEvaluate can sometimes help ensure timing.
                // If you still have issues, you can try re-adding it here.
                from(components["release"]) // Already handled by singleVariant("release") usually


                groupId = "com.github.forteanjo"
                artifactId = "extensions"
                version = "1.0.0"

                // Add these for sources and Javadoc
                artifact(tasks.named("sourcesJar")) // Assumes you have a sourcesJar task (see below)
//            artifact(tasks.named("javadocJar")) // Assumes you have a javadocJar task (see below)

                pom {
                    name.set(project.name) // Or a custom name
                    description.set("Useful Kotlin extensions for Android development.") // Example description
                    url.set("https://github.com/forteanjo/ExtensionsLibrary") // Example URL

                    licenses {
                        license {
                            name.set("The MIT License") // Or your chosen license
                            url.set("http://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("forteanjo")
                            name.set("Your Name")
                            email.set("your.email@example.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com/forteanjo/ExtensionsLibrary.git")
                        developerConnection.set("scm:git:ssh://github.com/forteanjo/ExtensionsLibrary.git")
                        url.set("https://github.com/forteanjo/ExtensionsLibrary/tree/main")
                    }
                }
            }

            // Option B (Safer if you're unsure of the exact auto-generated publication name,
            // but assumes you only have ONE publication being created by android.publishing):
            // withType<MavenPublication>().configureEach {
            //     // This will apply to ANY MavenPublication. Be careful if you have others.
            //     if (name == "release") { // Double-check the name if using this more general approach
            //         groupId = "com.github.forteanjo"
            //         artifactId = "extensions"
            //         version = "1.0.0"
            //
            //         pom { /* ... as above ... */ }
            //     }
            // }
        }

        repositories {
            maven {
                name = "localRelease"
                url = uri("${layout.buildDirectory}/repo")
            }
        }
    }
}
