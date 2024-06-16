package com.illiarb.catchup.service.di

import com.illiarb.catchup.core.network.HttpClient
import com.illiarb.catchup.core.network.di.NetworkComponent
import com.illiarb.catchup.service.network.CatchupService
import com.illiarb.catchup.service.network.CatchupServiceRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class CatchupServiceComponent(
  @Component val networkComponent: NetworkComponent,
) {

  abstract val service: CatchupService

  @Provides
  protected fun provideCatchupService(httpClient: HttpClient): CatchupService {
    return CatchupServiceRepository(httpClient)
  }
}
