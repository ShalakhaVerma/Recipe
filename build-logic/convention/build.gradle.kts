import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}
group = "com.coles.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin{
    plugins{
        register("androidApplication") {
            id = "coles.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "coles.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "coles.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "coles.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeatureCompose") {
            id = "coles.android.feature.compose"
            implementationClass = "AndroidFeatureComposeConventionPlugin"
        }

        register("androidRoom") {
            id = "coles.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidHilt") {
            id = "coles.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidFirebase") {
            id = "coles.android.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }

        register("androidRetrofit") {
            id = "coles.android.retrofit"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }

        register("jvmLibrary") {
            id = "coles.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}