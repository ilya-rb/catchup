package com.illiarb.catchup.service.domain

enum class NewsSource(val key: String) {
  IrishTimes(key = "irishtimes"),
  HackerNews(key = "hackernews"),
  Dou(key = "dou"),
  Unknown(key = "unknown");

  companion object {

    fun fromKey(key: String): NewsSource {
      return NewsSource.entries.first { it.key == key }
    }
  }
}
