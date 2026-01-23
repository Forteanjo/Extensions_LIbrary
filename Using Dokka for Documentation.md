# 🎯 Should *you* use Dokka for your JitPack library?

If your goal is simply to **publish the library**, you don’t *need* Dokka.
But if you want:
- clean API documentation
- something better than Javadoc
- a tool that won’t break under **AGP 9**
- a professional presentation for your library
  
…then yes, Dokka is absolutely the right choice.

Most modern Android/Kotlin libraries use Dokka instead of Javadoc now.

---

# 🎯 Set up a polished Dokka configuration tailored to your library 

Set your library set up with Dokka in a clean, AGP‑9‑friendly way — including source links, package options, and GitHub Pages publishing.

---

## 📘 Step 1 — Add the Dokka plugin

In your root or library module ```build.gradle.kts```:

```kotlin
plugins {
    id("org.jetbrains.dokka") version "2.1.0"
}
```

---
## 📁 Step 2 — (Android) Make sure Dokka sees your sources

Dokka automatically picks up:
- Kotlin sources
- Java sources
- Android source sets
- KSP/KAPT generated sources (if configured)

You don’t need to manually wire anything for AGP 9 — Dokka already uses the new Android Components APIs internally.

---
## 🧪 Step 3 — Run Dokka locally to verify

From your project root:
```text
./gradlew dokkaHtml
```

This generates documentation at:
```text
build/dokka/html/
```

Open ```index.html``` in a browser to check the output.

---
## 📦 Step 4 — (Optional) Configure Dokka for nicer output
You can add a dokka block to customize things like:
- source links
- package visibility
- module name
- footer/header
- GitHub links
  
Here’s a clean, modern configuration:
```kotlin
dokka {
    dokkaSourceSets {
        named("main") {
            displayName.set("Library API")
            includes.from("README.md")

            // Optional: link to your GitHub sources
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(
                    uri("https://github.com/YourUser/YourRepo/tree/main/src/main/kotlin").toURL()
                )
                remoteLineSuffix.set("#L")
            }

            // Optional: hide internal packages
            perPackageOption {
                matchingRegex.set(".*internal.*")
                suppress.set(true)
            }
        }
    }
}
```
This is entirely compatible with AGP 9.

---
## 🚀 Step 5 — (Optional) Publish Dokka docs to GitHub Pages
If you want your docs publicly visible:
- Create a branch named gh-pages
- Add a GitHub Action that copies build/dokka/html into that branch
- Enable GitHub Pages for that branch

---
## 🧩 Step 6 — JitPack compatibility
Good news: **Dokka does not interfere with JitPack**.

JitPack only cares that:
- your project builds
- your ```maven-publish``` block is correct
- you tag a release

Dokka runs independently and doesn’t affect the build artifacts

---
## 🎯 Summary
You now have:
- A fully AGP‑9‑compatible documentation generator
- A modern replacement for Javadoc
- A setup that works perfectly with JitPack
- Optional enhancements for GitHub Pages

---
# 🚀 GitHub Action: Auto‑Publish Dokka Docs to GitHub Pages

Save this as:
```text
.github/workflows/publish-docs.yml
```

```yaml
name: Publish Dokka Docs

on:
  push:
    branches: [ main ]
    tags: [ '*' ]

permissions:
  contents: write

jobs:
  build-and-publish:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: 17

      - name: Grant execute permission for Gradlew
        run: chmod +x gradlew

      - name: Build Dokka HTML
        run: ./gradlew dokkaHtml

      - name: Deploy to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: build/dokka/html
          publish_branch: gh-pages
          force_orphan: true
```

---
## 🧠 How this work

### ✔ Builds your Dokka docs

```./gradlew dokkaHtml``` generates HTML docs into ```build/dokka/html```.

### ✔ Publishes them to the ```gh-pages``` branch

The action creates or updates a branch named ```gh-pages``` containing only the documentation.

### ✔ GitHub Pages serves the docs
In your repo:

**Settings → Pages → Branch: ```gh-pages``` → ```/```**

After that, your docs will be live at:

```html
https://<your-username>.github.io/<your-repo>/
```
