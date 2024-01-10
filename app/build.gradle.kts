plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.simplifier.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.simplifier.myapplication"
        minSdk = 26
        targetSdk = 33
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

    flavorDimensions += "environment"
    flavorDimensions += "api"

    productFlavors {
        create("internal") {
            dimension = "environment"
            applicationIdSuffix = ".internal"
        }

        create("external") {
            dimension = "environment"
            applicationIdSuffix = ".external"
        }

        create("staging") {
            dimension = "api"
        }

        create("prod") {
            dimension = "api"
        }

        /*
        Flavors can also add diff sdk, version codes, version name
         */
    }

    sourceSets {

        getByName("internal") {
            java.setSrcDirs(listOf("src/internal/java", "src/main/java"))
            res.setSrcDirs(listOf("src/internal/res"))
            manifest.srcFile("src/internal/AndroidManifest.xml")
        }

        getByName("external") {
            java.setSrcDirs(listOf("src/external/java", "src/main/java"))
            res.setSrcDirs(listOf("src/external/res"))
            manifest.srcFile("src/external/AndroidManifest.xml")
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //visible sa dalawa
    implementation("androidx.compose.material3:material3")

    "internalImplementation"("com.google.accompanist:accompanist-permissions:0.28.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}