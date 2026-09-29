plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {

    namespace = "com.uniguide.app"

    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.uniguide.app"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // ==============================
    // Existing Android dependencies
    // ==============================

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.material)


    // ==============================
    // Android Splash Screen
    // ==============================

    implementation("androidx.core:core-splashscreen:1.2.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")

// ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")


    // ==============================
    // Jetpack Compose
    // ==============================

    val composeBom = platform(libs.androidx.compose.bom)

    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    implementation("androidx.core:core-splashscreen:1.2.0")
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.runtime)

    implementation("androidx.compose.foundation:foundation")

    implementation(libs.androidx.ui.tooling.preview)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)


    // ==============================
    // Testing
    // ==============================

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}