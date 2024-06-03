package di

import core.network.HttpClient
import network.CatchupService
import network.CatchupServiceRepository
import org.koin.dsl.module

val CatchupServiceModule = module {
  factory<CatchupServiceRepository> {
    CatchupServiceRepository(
      httpClient = get<HttpClient>(),
    )
  }
  factory<CatchupService> {
    CatchupServiceRepository(
      httpClient = get<HttpClient>()
    )
  }
}