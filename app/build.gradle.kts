plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("io.realm.kotlin")
    id ("com.google.dagger.hilt.android") version "2.41" apply false
    id ("realm-android")    //id ("com.google.gms.google-services") version "4.4.1'"
}


android {
    namespace = "com.example.qlnongsan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.qlnongsan"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // add realm
    //implementation ("com.google.firebase:firebase-analytics")
    implementation ("io.realm.kotlin:library-base:1.16.0")
    // If using Device Sync
    implementation ("io.realm.kotlin:library-sync:2.0.0")
    // If using coroutines with the SDK
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    //xong thủ tục add realm
}


