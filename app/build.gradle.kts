plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.argz.issue3148"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            /*** Solution provided on https://github.com/firebase/firebase-android-sdk/issues/2665#issuecomment-849897741
             * Scenario A: use classpath 'com.google.firebase:firebase-crashlytics-gradle:2.5.2'
             * 1. Uncomment the lines below [line 41 - 51]
             * 2. Run ./gradlew -q printExt
             * 3. This is displayed => "extension = firebaseCrashlytics + CrashlyticsExtension"
             *
             * (Scenario B shows that the solution provided might not be working)
             * Scenario B: use classpath 'com.google.firebase:firebase-crashlytics-gradle:2.8.0'
             * 1. Uncomment the lines below [line 41 - 51]
             * 2. Run ./gradlew -q printExt
             * 3. This is NOT displayed => "extension = firebaseCrashlytics + CrashlyticsExtension"
             *
             *
             * This means firebaseCrashlytics is not included in the build when using versions 2.6.0+
             *
             *
             */
//            project.extensions.extensionsSchema.forEach { extension ->
//                println("extension = ${extension.name} + ${extension.publicType.simpleName}")
//            }
//
//            val flavorName = "argz"
//            configure<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension> {
//                mappingFileUploadEnabled = false
//                nativeSymbolUploadEnabled = true
//                unstrippedNativeLibsDir = "build/intermediates/merged_native_libs/$flavorName/out/lib/"
//                strippedNativeLibsDir = "build/intermediates/stripped_native_libs/$flavorName/out/lib/"
//            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    /*** https://github.com/firebase/firebase-android-sdk/issues/3148
     * Scenario C: use classpath 'com.google.firebase:firebase-crashlytics-gradle:2.5.2'
     * 0. Comment the code [line 41 - 51]
     * 1. Uncomment the code below [line 79 - 94]
     * 2. Run ./gradlew -q printExt
     * 3. This is displayed => "extension = firebaseCrashlytics + CrashlyticsExtension"
     *      This means that firebaseCrashlytics is visible when using version 2.5.2
     *
     *
     * Scenario D: use classpath 'com.google.firebase:firebase-crashlytics-gradle:2.8.0'
     * 0. Comment the code [line 41 - 51]
     * 1. Uncomment the code below [line 79 - 94]
     * 2. Sync gradle
     * 3. See error that "Extension of type 'CrashlyticsExtension' does not exist."
     *      This is due to the fact that even though gradle was able to build and sync on Scenario A & B,
     *      CrashlyticsExtension is still not registered on 2.6.0+ onwards, which is causing the error below
     */
//    androidComponents {
//        onVariants {
//            project.extensions.extensionsSchema.forEach { extension ->
//                println("extension = ${extension.name} + ${extension.publicType.simpleName}")
//            }
//
//            val flavorName = "argz"
//
//            configure<com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension> {
//                mappingFileUploadEnabled = false
//                nativeSymbolUploadEnabled = true
//                unstrippedNativeLibsDir = "build/intermediates/merged_native_libs/$flavorName/out/lib/"
//                strippedNativeLibsDir = "build/intermediates/stripped_native_libs/$flavorName/out/lib/"
//            }
//        }
//    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}