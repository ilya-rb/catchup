package com.illiarb.catchup.service.network.dto

import com.illiarb.catchup.service.domain.Article
import com.illiarb.catchup.service.domain.NewsSource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ArticleDto(
  @SerialName("id") val id: String,
  @SerialName("title") val title: String,
  @SerialName("description") val description: String?,
  @SerialName("link") val link: String,
  @SerialName("source") val source: String,
  @SerialName("tags") val tags: List<String>?,
) {

  fun asArticle(): Article {
    return Article(
      id = id,
      title = title.trimIndent(),
      description = description?.trimIndent(),
      link = link,
      source = source.asNewsSource(),
      tags = tags.orEmpty(),
    )
  }

  private fun String.asNewsSource(): NewsSource {
    return when (this) {
      "irishtimes" -> NewsSource.IrishTimes
      "hackernews" -> NewsSource.HackerNews
      "dou" -> NewsSource.Dou
      else -> NewsSource.Unknown
    }
  }
}
