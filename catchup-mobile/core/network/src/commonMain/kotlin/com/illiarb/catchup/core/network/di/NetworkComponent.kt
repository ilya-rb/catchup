package com.illiarb.catchup.core.network.di

import com.illiarb.catchup.core.network.NetworkConfig
import com.illiarb.catchup.core.network.createKtorClient
import com.illiarb.catchup.core.network.HttpClient
import com.illiarb.catchup.core.network.DefaultHttpClient
import com.illiarb.catchup.core.coroutines.AppDispatchers
import io.ktor.client.HttpClient as KtorClient
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class NetworkComponent {

  @Provides
  fun provideKtorClient(config: NetworkConfig): KtorClient {
    return createKtorClient(config)
  }

  @Provides
  fun provideNetworkConfig(): NetworkConfig {
    return NetworkConfig(
      apiUrl = "", // TODO: Expose url
      connectTimeoutSeconds = 10,
      readTimeoutSeconds = 10,
      writeTimeoutSeconds = 10,
    )
  }

  @Provides
  fun provideHttpClient(config: NetworkConfig, httpClient: KtorClient): HttpClient {
    return DefaultHttpClient(
      baseUrl = config.apiUrl,
      ktorHttpClient = httpClient,
      appDispatchers = AppDispatchers(),
    )
  }
}


