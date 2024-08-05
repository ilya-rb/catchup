package com.illiarb.catchup.service.network.dto

import com.illiarb.catchup.service.domain.NewsSource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SupportedSourcesResponse(
  @SerialName("sources")
  val sources: List<String>,
) {

  fun asNewsSources(): Set<NewsSource> {
    return sources.map(NewsSource.Companion::fromKey).toSet()
  }
}
