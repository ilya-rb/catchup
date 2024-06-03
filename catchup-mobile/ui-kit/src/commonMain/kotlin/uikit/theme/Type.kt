package uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import catchup_mobile.ui_kit.generated.resources.*
import catchup_mobile.ui_kit.generated.resources.Res
import catchup_mobile.ui_kit.generated.resources.montserrat_light
import catchup_mobile.ui_kit.generated.resources.montserrat_medium
import catchup_mobile.ui_kit.generated.resources.montserrat_normal
import org.jetbrains.compose.resources.Font

internal val UiKitTypography: Typography
  @Composable get() {
    val fontMamily = FontFamily(
      Font(Res.font.montserrat_light, FontWeight.Light),
      Font(Res.font.montserrat_normal, FontWeight.Normal),
      Font(Res.font.montserrat_medium, FontWeight.Medium),
      Font(Res.font.montserrat_semibold, FontWeight.SemiBold),
      Font(Res.font.montserrat_bold, FontWeight.Bold),
    )

    // Default Material 3 typography values
    val baseline = Typography()

    return Typography(
      bodyLarge = baseline.bodyLarge.copy(fontFamily = fontMamily),
      bodyMedium = baseline.bodyMedium.copy(fontFamily = fontMamily),
      bodySmall = baseline.bodySmall.copy(fontFamily = fontMamily),
      displayLarge = baseline.displayLarge.copy(fontFamily = fontMamily),
      displayMedium = baseline.displayMedium.copy(fontFamily = fontMamily),
      displaySmall = baseline.displaySmall.copy(fontFamily = fontMamily),
      headlineLarge = baseline.headlineLarge.copy(fontFamily = fontMamily),
      headlineMedium = baseline.headlineMedium.copy(fontFamily = fontMamily),
      headlineSmall = baseline.headlineSmall.copy(fontFamily = fontMamily),
      labelLarge = baseline.labelLarge.copy(fontFamily = fontMamily),
      labelMedium = baseline.labelMedium.copy(fontFamily = fontMamily),
      labelSmall = baseline.labelSmall.copy(fontFamily = fontMamily),
      titleLarge = baseline.titleLarge.copy(fontFamily = fontMamily),
      titleMedium = baseline.titleMedium.copy(fontFamily = fontMamily),
      titleSmall = baseline.titleSmall.copy(fontFamily = fontMamily),
    )
  }