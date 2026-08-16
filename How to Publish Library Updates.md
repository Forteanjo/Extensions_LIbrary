# How to Publish Library Updates to JitPack.io

JitPack builds your library directly from your GitHub repository, so publishing is mostly about preparing your Gradle setup and creating a GitHub release.

## Step 1 - Push your library to GitHub

JitPack only works with Git repositories hosted on GitHub. 

Make sure your project builds cleanly.

## Step 2 - Add the maven-publish plugin

Your library module needs a Maven publication.

```kotlin
plugins {
    `maven-publish`
}
```
Then configure the publication

```kotlin
publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.<YourUser>"
            artifactId = "<YourLibraryName>"
            version = "1.0.0"

            afterEvaluate {
                from(components["java"])
            }
        }
    }
}
```
## Step 3 - (Optional but recommended) Add a *jitpack.yml*

If you need a specific JDK or custom build steps, add this to your repo root

```yaml
jdk:
  - openjdk17
```
Common for Android/Kotlin projects.

## Step 4 - Verify local publishing

Before pushing, confirm your library can publish locally (paste into Terminal)
```
./gradlew publishToMavenLocal
```
## Step 5 - Create a GitHub Release (Tag)

JitPack builds from tags.
Go to **GitHub** → **Releases** → **Draft a new release** and create a tag like *v1.0.0*.

## Step 6 - Go to JitPack.io and build your library

Visit:

https://jitpack.io/#YourUser/YourRepo (jitpack.io in Bing)

## Step 7 - Consumers add your library like this

In their ```settings.gradle```:

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }
}
```

In their module ```build.gradle```:

```kotlin
dependencies {
    implementation("com.github.YourUser:YourRepo:1.0.0")
}
```
## 🎉 That’s it
Once the tag builds successfully, your library is live and anyone can depend on it.
