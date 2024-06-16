package com.illiarb.catchup.core.network

import io.ktor.client.HttpClient

data class NetworkConfig(
  val apiUrl: String,
  val connectTimeoutSeconds: Long,
  val readTimeoutSeconds: Long,
  val writeTimeoutSeconds: Long,
)

expect fun createKtorClient(config: NetworkConfig): HttpClient
