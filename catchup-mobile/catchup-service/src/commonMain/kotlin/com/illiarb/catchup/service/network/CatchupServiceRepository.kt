package com.illiarb.catchup.service.network

import com.illiarb.catchup.service.domain.Article
import com.illiarb.catchup.service.domain.NewsSource
import com.illiarb.catchup.service.network.dto.ArticleDto
import com.illiarb.catchup.service.network.dto.ArticlesResponse
import com.illiarb.catchup.core.network.HttpClient
import com.illiarb.catchup.service.network.dto.SupportedSourcesResponse
import io.ktor.client.call.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import me.tatarka.inject.annotations.Inject

interface CatchupService {

  fun collectLatestNewsFrom(source: NewsSource): Flow<List<Article>>

  fun collectAvailableSources(): Flow<Set<NewsSource>>
}

@Inject
internal class CatchupServiceRepository(
  private val httpClient: HttpClient,
) : CatchupService {

  override fun collectLatestNewsFrom(source: NewsSource): Flow<List<Article>> = flow {
    val articles = httpClient.get(
      path = "news",
      parameters = mapOf("source" to source.key)
    ).map {
      val response = it.body<ArticlesResponse>()
      response.articles.map(ArticleDto::asArticle)
    }
    emit(articles.getOrElse { emptyList() })
  }.catch {
    emit(emptyList())
  }

  override fun collectAvailableSources(): Flow<Set<NewsSource>> = flow {
    val sources = httpClient.get(path = "supported_sources").map {
      val response = it.body<SupportedSourcesResponse>()
      response.asNewsSources()
    }
    emit(sources.getOrThrow())
  }
}
