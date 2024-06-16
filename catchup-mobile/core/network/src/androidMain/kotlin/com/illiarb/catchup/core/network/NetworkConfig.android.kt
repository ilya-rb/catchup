package com.illiarb.catchup.core.network

import com.illiarb.catchup.core.logging.Logger
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit
import io.ktor.client.plugins.logging.Logger as KtorLogger

actual fun createKtorClient(config: NetworkConfig): HttpClient {
  return HttpClient(OkHttp) {
    engine {
      config {
        retryOnConnectionFailure(true)
        connectTimeout(config.connectTimeoutSeconds, TimeUnit.SECONDS)
        readTimeout(config.readTimeoutSeconds, TimeUnit.SECONDS)
        writeTimeout(config.writeTimeoutSeconds, TimeUnit.SECONDS)
      }
    }

    install(ContentNegotiation) {
      json(Json {
        prettyPrint = true
      })
    }

    install(Logging) {
      level = LogLevel.HEADERS
      logger = object : KtorLogger {
        override fun log(message: String) {
          Logger.v(tag = "AndroidHttpClient") { message }
        }
      }
    }
  }
}
