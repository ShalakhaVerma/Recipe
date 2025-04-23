plugins {
    alias(libs.plugins.coles.android.library)
    alias(libs.plugins.coles.android.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.coles.core.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.model.entity)
    implementation(projects.model.apiresponse)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.hilt.android.core)
    kapt(libs.hilt.compiler)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.gson)

}