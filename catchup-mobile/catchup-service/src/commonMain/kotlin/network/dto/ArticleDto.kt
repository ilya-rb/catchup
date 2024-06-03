package network.dto

import domain.Article
import domain.NewsSource
import kotlinx.serialization.SerialName

internal data class ArticleDto(
  @SerialName("id") val id: String,
  @SerialName("title") val title: String,
  @SerialName("description") val description: String?,
  @SerialName("link") val link: String,
  @SerialName("source") val source: String,
) {

  fun asArticle(): Article {
    return Article(
      id = id,
      title = title,
      description = description,
      link = link,
      source = source.asNewsSource(),
    )
  }

  private fun String.asNewsSource(): NewsSource {
    return when (this) {
      "irishtimes" -> NewsSource.IrishTimes
      else -> NewsSource.Unknown
    }
  }
}
