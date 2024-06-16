package com.illiarb.catchup.uikit.image

import coil3.util.Logger as CoilLogger
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.memory.MemoryCache
import com.illiarb.catchup.core.appinfo.AppEnvironment
import com.illiarb.catchup.core.logging.Logger

internal fun createImageLoader(
  context: PlatformContext,
  environment: AppEnvironment,
): ImageLoader =
  ImageLoader.Builder(context)
    .memoryCache {
      MemoryCache.Builder()
        .maxSizePercent(context, percent = 0.1)
        .build()
    }
    .apply {
      if (environment.isDev()) {
        logger(Logger.asCoilLogger())
      }
    }
    .build()

private fun Logger.asCoilLogger() = object : CoilLogger {

  override var minLevel: CoilLogger.Level = CoilLogger.Level.Debug

  override fun log(tag: String, level: CoilLogger.Level, message: String?, throwable: Throwable?) {
    @Suppress("NAME_SHADOWING")
    val message = message.orEmpty()

    when (level) {
      CoilLogger.Level.Verbose -> Logger.v(tag, throwable) { message }
      CoilLogger.Level.Debug -> Logger.d(tag, throwable) { message }
      CoilLogger.Level.Info -> Logger.i(tag, throwable) { message }
      CoilLogger.Level.Warn -> Logger.w(tag, throwable) { message }
      CoilLogger.Level.Error -> Logger.e(tag, throwable) { message }
    }
  }

}
