package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightColorScheme = lightColorScheme(
  primary = SetuBluePrimary,
  onPrimary = PureWhite,
  primaryContainer = SetuBlueLight,
  onPrimaryContainer = SetuBlueDark,
  secondary = SetuOrange,
  onSecondary = PureWhite,
  secondaryContainer = SetuOrangeContainer,
  onSecondaryContainer = SetuOrange,
  tertiary = SetuIndigo,
  onTertiary = PureWhite,
  tertiaryContainer = SetuIndigoLight,
  onTertiaryContainer = SetuIndigo,
  background = Slate50,
  onBackground = Slate900,
  surface = PureWhite,
  onSurface = Slate900,
  surfaceVariant = Slate100,
  onSurfaceVariant = Slate600,
  outline = Slate200,
  outlineVariant = Slate400
)

val SetuRoundedShapes = Shapes(
  extraSmall = RoundedCornerShape(8.dp),
  small = RoundedCornerShape(12.dp),
  medium = RoundedCornerShape(16.dp),
  large = RoundedCornerShape(20.dp),
  extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun KargarTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = LightColorScheme,
    typography = Typography,
    shapes = SetuRoundedShapes,
    content = content
  )
}

@Composable
fun SetuHelpTheme(content: @Composable () -> Unit) {
  KargarTheme(content = content)
}
