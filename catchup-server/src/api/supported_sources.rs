use actix_web::{web, HttpResponse};
use serde::Serialize;

use crate::domain::NewsSource;

#[derive(Serialize)]
pub struct Response {
    sources: Vec<String>,
}

#[tracing::instrument(name = "Querying supported sources")]
pub async fn supported_sources() -> HttpResponse {
    let response = web::Json(Response { sources: NewsSource::keys() });

    HttpResponse::Ok().json(response)
}