plugins {
  id("com.illiarb.catchup.android.library")
  id("com.illiarb.catchup.kotlin.multiplatform")
}

android {
  namespace = "com.illiarb.catchup.core.arch"
}

kotlin {
  sourceSets {
    commonMain.dependencies {
      implementation(libs.circuit.core)
    }
  }
}

