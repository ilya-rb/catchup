plugins {
  id("com.illiarb.catchup.android.library")
  id("com.illiarb.catchup.kotlin.multiplatform")
  id("com.illiarb.catchup.kotlin.inject")
  id("com.illiarb.catchup.compose")
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.materialIconsExtended)
      implementation(compose.ui)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)

      implementation(libs.circuit.core)
      implementation(libs.circuit.overlay)

      implementation(libs.coil.core)
      implementation(libs.coil.network)
      implementation(libs.coil.compose)
      implementation(libs.shimmer)
      implementation(libs.lottie.core)

      implementation(projects.core.logging)
      implementation(projects.core.appInfo)
      implementation(projects.uiKit.imageLoader)
    }
  }
}

android {
  namespace = "com.illiarb.catchup.uikit.core"

  sourceSets["main"].resources.srcDirs("src/commonMain/composeResources")

  buildFeatures {
    compose = true
  }

  dependencies {
    debugImplementation(compose.uiTooling)
  }
}

