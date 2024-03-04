@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "com.sayempro.noteapproomdatabase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sayempro.noteapproomdatabase"
        minSdk = 21
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
    packagingOptions {
        exclude ("META-INF/atomicfu.kotlin_module")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {
    implementation (libs.appcompat)
    implementation (libs.androidx.activity.ktx)

    // Dependencies for working with Architecture components
    // You'll probably have to update the version numbers in build.gradle (Project)

    // Room components
    implementation (libs.androidx.room.ktx)
    kapt (libs.androidx.room.compiler)
    androidTestImplementation (libs.androidx.room.testing)

    // Lifecycle components
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation (libs.androidx.lifecycle.common.java8)

    // Kotlin components
    implementation (libs.kotlin.stdlib.jdk7)
    api (libs.kotlinx.coroutines.core)
    api (libs.kotlinx.coroutines.android)

    // UI
    implementation (libs.constraintlayout)
    implementation (libs.material)

    // Testing
    testImplementation (libs.junit)
    androidTestImplementation (libs.androidx.arch.core.testing)
//    androidTestImplementation ("androidx.test.espresso:espresso-core:$rootProject.espressoVersion", {
//        exclude group: "com.android.support"; module: "support-annotations"
//    })
    androidTestImplementation (libs.androidx.test.ext.junit)
}

//
//dependencies {
//
//    implementation(libs.core.ktx)
//    implementation(libs.appcompat)
//    implementation(libs.material)
//    implementation(libs.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.test.ext.junit)
//    androidTestImplementation(libs.espresso.core)
//}