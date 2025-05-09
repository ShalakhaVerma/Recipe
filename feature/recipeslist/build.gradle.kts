plugins {
    alias(libs.plugins.coles.android.library)
    alias(libs.plugins.coles.android.hilt)
    alias(libs.plugins.coles.android.library.compose)
}

android {
    namespace = "com.coles.feature.recipeslist"

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.designcomponents)
    implementation(projects.core.testing)
    implementation(projects.model.entity)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    testImplementation(libs.test.mockito)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.hilt.navigation)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.gson)

    implementation(libs.hilt.android.core)
    // AndroidX Test - Hilt testing
    androidTestImplementation(libs.hilt.android.testing)
}