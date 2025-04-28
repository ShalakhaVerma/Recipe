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
    implementation(libs.androidx.material3.android)
    implementation(libs.hilt.android.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.extjunit)
    androidTestImplementation(libs.test.compose.ui.junit)
    implementation(libs.androidx.test.rules)
    implementation(libs.kotlinx.coroutines.android)

}