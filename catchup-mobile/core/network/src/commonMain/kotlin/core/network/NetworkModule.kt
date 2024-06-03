package core.network

import core.coroutines.AppDispatchers
import org.koin.dsl.module
import io.ktor.client.HttpClient as KtorClient

val NetworkModule = module {
  single<KtorClient> {
    createKtorClient(config = get<NetworkConfig>())
  }

  single<HttpClient> {
    DefaultHttpClient(
      baseUrl = get<NetworkConfig>().apiUrl,
      ktorHttpClient = get<KtorClient>(),
      appDispatchers = AppDispatchers(),
    )
  }

  factory<NetworkConfig> {
    NetworkConfig(
      apiUrl = "", // TODO: Expose url
      connectTimeoutSeconds = 10,
      readTimeoutSeconds = 10,
      writeTimeoutSeconds = 10,
    )
  }
}

expect fun createKtorClient(config: NetworkConfig): KtorClient