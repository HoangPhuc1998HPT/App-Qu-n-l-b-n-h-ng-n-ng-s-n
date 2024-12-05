// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("io.realm.kotlin") version "1.16.0" apply false
}
buildscript {
    repositories {
        repositories {
            gradlePluginPortal()
            google()
            mavenCentral()
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath ("com.google.gms:google-services:3.0.0")
        classpath ("io.realm:realm-gradle-plugin:10.15.1")
    }
}