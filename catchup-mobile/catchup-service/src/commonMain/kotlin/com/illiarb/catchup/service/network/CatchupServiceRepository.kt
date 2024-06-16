package com.illiarb.catchup.service.network

import com.illiarb.catchup.service.domain.Article
import com.illiarb.catchup.service.domain.NewsSource
import com.illiarb.catchup.service.network.dto.ArticleDto
import com.illiarb.catchup.service.network.dto.ArticlesResponse
import com.illiarb.catchup.core.network.HttpClient
import io.ktor.client.call.*
import me.tatarka.inject.annotations.Inject

interface CatchupService {

  suspend fun getLatestNewsFrom(source: NewsSource): Result<List<Article>>
}

@Inject
internal class CatchupServiceRepository(
  private val httpClient: HttpClient,
) : CatchupService {

  override suspend fun getLatestNewsFrom(source: NewsSource): Result<List<Article>> {
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
