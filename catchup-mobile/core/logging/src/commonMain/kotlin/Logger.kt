import io.github.aakira.napier.Napier

object Logger {

  fun i(tag: String, messageLazy: () -> String) {
    Napier.i(tag = tag, message = messageLazy)
  }

  fun d(tag: String, messageLazy: () -> String) {
    Napier.d(tag = tag, message = messageLazy)
  }

  fun v(tag: String, messageLazy: () -> String) {
    Napier.v(tag = tag, message = messageLazy)
  }

  fun e(tag: String, error: Throwable? = null, messageLazy: () -> String) {
    Napier.e(tag = tag, throwable = error, message = messageLazy)
  }
}