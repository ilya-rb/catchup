use sqlx::PgPool;

use crate::domain::{Article, NewsSource};
use crate::repository;

pub async fn get_latest_news(db: &PgPool) -> Result<Vec<Article>, sqlx::Error> {
    repository::article::get_by_source(db, NewsSource::IrishTimes).await
}
