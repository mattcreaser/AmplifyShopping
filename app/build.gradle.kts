plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.github.mattcreaser.amplifyshopping"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        isCoreLibraryDesugaringEnabled = true
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Deps.core_ktx)

    implementation(Deps.compose_ui)
    implementation(Deps.compose_material)
    implementation(Deps.compose_tooling)
    implementation(Deps.compose_activity)
    implementation(Deps.compose_viewmodel)

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.0")

    // Amplify plugins
    implementation(Deps.amplify_core)
    implementation(Deps.amplify_api)
    implementation(Deps.amplify_datastore)

    // Support for Java 8 features
    coreLibraryDesugaring(Deps.desugar)
}