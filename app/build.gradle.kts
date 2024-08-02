import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.findmore.reelraves"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.findmore.reelraves"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }

        //load the values from .properties file
        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        val baseUrl = properties.getProperty("base_url") ?: ""
        val imageBaseUrl = properties.getProperty("image_base_url") ?: ""
        val apiKey = properties.getProperty("tmdb_api_key") ?: ""
        val tmdbReadAccessToken = properties.getProperty("tmdb_read_access_token") ?: ""

        buildConfigField(
            type = "String", name = "BaseURL", value = baseUrl
        )
        buildConfigField(
            type = "String", name = "ImageBaseURL", value = imageBaseUrl
        )


        buildConfigField(
            type = "String", name = "TMDB_API_KEY", value = apiKey
        )

        buildConfigField(
            type = "String", name = "TMDB_ACCESS_TOKEN", value = tmdbReadAccessToken
        )
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }

        release {
            isDebuggable = false
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.preference.ktx)
 
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)

    //Room DB
    implementation(libs.androidx.room)
   // kapt(libs.androidx.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.androidx.room.ktx)

    //JetPack Lifecycles
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.paging)

    //HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)

    // Coil Image Loading
    implementation(libs.coil)
    implementation(libs.accompanist.coil)

    //Test
    testImplementation(libs.mockk)
    testImplementation(libs.junit)
}

kapt {
    correctErrorTypes = true
}