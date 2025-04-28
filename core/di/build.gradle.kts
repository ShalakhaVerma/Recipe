plugins {
    alias(libs.plugins.coles.android.library)
    alias(libs.plugins.coles.android.hilt)
}

android {
    namespace = "com.coles.core.di"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}