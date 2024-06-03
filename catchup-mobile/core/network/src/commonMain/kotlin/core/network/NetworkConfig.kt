package core.network

data class NetworkConfig(
  val apiUrl: String,
  val connectTimeoutSeconds: Long,
  val readTimeoutSeconds: Long,
  val writeTimeoutSeconds: Long,
)