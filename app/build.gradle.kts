plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "fi.mobiilikehitysprojektir13.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "fi.mobiilikehitysprojektir13.newsapp"
        minSdk = 29
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {
    implementation("org.osmdroid:osmdroid-android:6.1.16")
    implementation("tech.utsmankece:osm-android-compose:0.0.5")
    implementation(libs.coil.compose)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.koin.android)
    implementation(libs.koin.ktor)

    implementation(libs.navigation.compose)

    implementation(libs.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.core.ktx.v1101)
    implementation(libs.accompanist.permissions)

    implementation(libs.datastore.preferences)
    implementation(libs.datastore.core)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}