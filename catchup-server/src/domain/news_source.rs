use strum::IntoEnumIterator;
use strum_macros::EnumIter;

#[derive(Clone, Debug, PartialEq, serde::Serialize, EnumIter)]
pub enum NewsSource {
    IrishTimes,
    HackerNews,
    Dou,
}

impl NewsSource {
    pub fn keys() -> Vec<String> {
        NewsSource::iter().map(|n| n.into()).collect()
    }
}

impl TryFrom<&str> for NewsSource {
    type Error = String;

    fn try_from(value: &str) -> Result<Self, Self::Error> {
        match value {
            "irishtimes" => Ok(NewsSource::IrishTimes),
            "hackernews" => Ok(NewsSource::HackerNews),
            "dou" => Ok(NewsSource::Dou),
            _ => Err(format!("Unsupported source {}", value)),
        }
    }
}

impl From<NewsSource> for String {
    fn from(value: NewsSource) -> Self {
        String::from(match value {
            NewsSource::IrishTimes => "irishtimes",
            NewsSource::HackerNews => "hackernews",
            NewsSource::Dou => "dou",
        })
    }
}
