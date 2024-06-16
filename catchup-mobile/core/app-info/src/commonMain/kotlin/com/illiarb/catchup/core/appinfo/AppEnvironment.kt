package com.illiarb.catchup.core.appinfo

enum class AppEnvironment {
  DEV,
  PROD;

  fun isDev() = this == DEV
}
