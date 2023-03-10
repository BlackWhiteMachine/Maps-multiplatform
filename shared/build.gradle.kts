plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.20")

                implementation("io.ktor:ktor-client-json:2.1.3")
                implementation("io.ktor:ktor-client-serialization:2.1.3")
                implementation("io.ktor:ktor-client-content-negotiation:2.1.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.3")
                implementation("io.ktor:ktor-client-logging:2.1.3")

                implementation("org.kodein.di:kodein-di:7.1.0")
            }
        }

//        androidMain {
//            dependencies {
//                implementation("io.ktor:ktor-client-android:2.1.3")
////                implementation(Dependencies.SqlDelight.android)
//            }
//        }
//
//        iosMain {
//            dependencies {
//                implementation("io.ktor:ktor-client-ios:2.1.3")
////                implementation(Dependencies.SqlDelight.ios)
//            }
//        }
//
//        desktopMain {
//            dependencies {
//                implementation("io.ktor:ktor-client-okhttp:2.1.3")
////                implementation(Dependencies.SqlDelight.desktop)
//            }
//        }
    }
}

android {
    namespace = "com.positronen.maps"
    compileSdk = 32
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
}
dependencies {
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("io.ktor:ktor-client-android:2.1.3")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
}
