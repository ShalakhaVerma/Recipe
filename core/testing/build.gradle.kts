plugins {
    alias(libs.plugins.coles.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.coles.testing"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.test.rules)
    implementation(libs.kotlinx.coroutines.android)
}