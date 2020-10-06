plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.letorriellec.dimitri.albumslibrary"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_ADDRESS", "\"https://spurd.pagesperso-orange.fr/\"")
    }
    buildTypes {
        getByName("release") {
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
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.0")
    implementation(kotlin("stdlib-jdk7", org.jetbrains.kotlin.config.KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.1")
    implementation("androidx.exifinterface:exifinterface:1.3.0")


    testImplementation("org.mockito:mockito-core:2.22.0")
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    implementation("androidx.recyclerview:recyclerview:1.1.0")

    implementation("androidx.room:room-rxjava2:2.2.5")

    implementation("com.squareup.picasso:picasso:2.71828")

    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0")

    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    testImplementation("io.mockk:mockk:1.10.0")
    androidTestImplementation("androidx.test:rules:1.3.0")

}
