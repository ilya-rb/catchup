package network

import core.network.HttpClient
import domain.Article
import domain.NewsSource
import io.ktor.client.call.*
import network.dto.ArticleDto
import network.dto.ArticlesResponse

interface CatchupService {

  suspend fun getLatestNewsFor(source: NewsSource): Result<List<Article>>
}

internal class CatchupServiceRepository(
  private val httpClient: HttpClient,
) : CatchupService {

  override suspend fun getLatestNewsFor(source: NewsSource): Result<List<Article>> {
    return httpClient.get(
      path = "news",
      parameters = mapOf("news_type" to source.asRequestKey())
    ).map {
      val response = it.body<ArticlesResponse>()
      response.articles.map(ArticleDto::asArticle)
    }
  }

  private fun NewsSource.asRequestKey(): String {
    return when (this) {
      NewsSource.IrishTimes -> "irishtimes"
      NewsSource.Unknown -> throw IllegalArgumentException("Can't request data for unknown source")
    }
  }
}