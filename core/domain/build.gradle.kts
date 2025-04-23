plugins {
    alias(libs.plugins.coles.android.library)
    alias(libs.plugins.coles.android.hilt)
}

android {
    namespace = "com.coles.core.domain"
}

dependencies {
    implementation(projects.model.entity)
    implementation(projects.model.apiresponse)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.hilt.android.core)

}