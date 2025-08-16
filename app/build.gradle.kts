import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "sco.carlukesoftware.extensionslibrary"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "sco.carlukesoftware.extensionslibrary"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        //noinspection EditedTargetSdkVersion
        versionCode = libs.versions.app.versionCode.get().toInt()
        versionName = libs.versions.app.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/notice.txt"
            excludes += "META-INF/ASL2.0"
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
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.annotation)

    implementation(libs.kotlin.extensions.library)

}
