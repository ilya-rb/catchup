package domain

data class Article(
  val id: String,
  val title: String,
  val description: String?,
  val link: String,
  val source: NewsSource,
)