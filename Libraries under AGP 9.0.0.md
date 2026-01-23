# Publishing Libraries under AGP 9.0.0

## Background

AGP 9.0.0 removes or breaks several of the APIs your snippet depends on:
- ```android.sourceSets``` → replaced by the new **Android Components DSL**
- ```android.bootClasspath``` → removed; replaced by ```androidComponents.sdkComponents.bootClasspath```
- ```Javadoc``` **tasks no longer work for Android sources**, because Android uses **Kotlin + Java + generated sources**, and AGP no longer wires them into the old Java toolchain.

So the old pattern:

```kotlin
tasks.withType<Javadoc> {
    source(android.sourceSets["main"].java.srcDirs)
    classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
    exclude("**/R.class", "**/BuildConfig.class")
}
```
…no longer works under AGP 9.

---

# ✅ The correct AGP 9 replacement

AGP 9 requires you to use the **Android Components DSL** and the new **SDK Components API**.

Here’s the modern equivalent:

```kotlin
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.SourceDirectories

plugins {
    id("com.android.library")
    kotlin("android")
}

androidComponents {
    onVariants { variant ->
        tasks.register("javadoc${variant.name.capitalize()}", Javadoc::class.java) {
            // Source: use the new variant API
            val javaSources = variant.sources.java?.all
            if (javaSources != null) {
                source(javaSources.map { it.asFileTree })
            }

            // Boot classpath: use sdkComponents
            val bootClasspath = sdkComponents.bootClasspath
            classpath = files(bootClasspath) + files(variant.compileClasspath)

            // Exclusions
            exclude("**/R.class", "**/BuildConfig.class")
        }
    }
}
```
---

## ⚠️ Important: Javadoc on Android is fragile

AGP 9 no longer guarantees:
- Java-only sources
- A stable bootClasspath
- That Javadoc can run on mixed Kotlin/Java modules

Most Android libraries now use **Dokka** instead of Javadoc.

If your goal is to publish documentation, the recommended modern setup is:

```kotlin
plugins {
    id("org.jetbrains.dokka") version "1.9.20"
}
```
And then:

```text
./gradlew dokkaHtml
```

# 🎯 If your goal is Publishing a library to JitPack

Publishing an Android/Kotlin library to **JitPack** works beautifully until AGP 8/9 enters the picture.

Previous versions failed because **AGP 9** removes the old **Java source + bootClasspath APIs**, and JitPack relies on your project building cleanly *before* it can publish.

---

# ⭐ A Clean, JitPack‑friendly setup without fighting AGP - the key insight

**You do *NOT* need Javadoc tasks at all for JitPack**.

JitPack only needs:

1. Your project to build successfully
2. A valid Maven publication (```maven-publish```)
3. A Git tag or release

Javadoc generation is optional, and for Android libraries it’s now discouraged because AGP 9 breaks the old Javadoc pipeline.

So the simplest and most reliable path is:

### 👉 Remove the ```tasks.withType<Javadoc>``` block entirely

It is no longer compatible with AGP 9.

---

## ✅ The correct AGP‑9‑compatible setup for JitPack

### 1. Add the ```maven-publish``` plugin

In your library module:

```kotlin
plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}
```

---

### 2. Add a publication block

**AGP 8+** requires you to configure publishing after evaluation

```kotlin
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.github.YourUser"
                artifactId = "YourLibraryName"
                version = "1.0.0"
            }
        }
    }
}
```

This is the exact pattern **JitPack** expects for Android libraries.

---

### 3. Push to **GitHub** and create a tag

Example:

```html
v1.0.0
```

---

### 4. Go to *JitPack*

Visit:

```html
https://jitpack.io/#YourUser/YourRepo
```

JitPack will detect your tag and build your library.

---

### 5. Consumers add your library like this

**settings.gradle**

```kotlin
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

**build.gradle**

```kotlin
dependencies {
    implementation("com.github.YourUser:YourRepo:1.0.0")
}
```

---

## 🎯 Summary

To publish your library on JitPack with AGP 9:

- **Delete the Javadoc task** (AGP 9 breaks it)
- **Use ```maven-publish``` with ```afterEvaluate```**
- **Tag** a **release**
- Let **JitPack** build it
