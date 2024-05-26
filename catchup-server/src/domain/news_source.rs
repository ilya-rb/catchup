#[derive(Clone, Debug, PartialEq, serde::Serialize)]
pub enum NewsSource {
    IrishTimes,
}

impl TryFrom<&str> for NewsSource {
    type Error = String;

    fn try_from(value: &str) -> Result<Self, Self::Error> {
        match value {
            "irishtimes" => Ok(NewsSource::IrishTimes),
            _ => Err(format!("Unsupported source {}", value)),
        }
    }
}

impl From<NewsSource> for String {
    fn from(value: NewsSource) -> Self {
        String::from(match value {
            NewsSource::IrishTimes => "irishtimes",
        })
    }
}
