import org.jetbrains.kotlin.fir.declarations.builder.buildScript

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    //val kotlinVersion by extra("1.9.25")
    val hiltVersion by extra("2.52")
    //val lifecycleVersion by extra("2.3.1")

    dependencies {
        //classpath("com.android.tools.build:gradle:8.6.0")
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}