package network.dto

import kotlinx.serialization.SerialName

internal class ArticlesResponse(
  @SerialName("articles")
  val articles: List<ArticleDto>,
)