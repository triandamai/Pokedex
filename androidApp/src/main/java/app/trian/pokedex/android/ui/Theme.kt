/*
 * Copyright © 2023 trian.app
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


package app.trian.pokedex.android.ui

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

@SuppressLint("ConflictingOnColor")
private val darkColorScheme = darkColors(
    onBackground = OnSecondary,
    onSurface = OnSurface,
    onError = ColorOnPrimary,
    primary = Primary,
    primaryVariant = PrimaryVariant,
    secondary = Secondary,
    secondaryVariant = SecondaryVariant,
    background = Background,
    surface = Surface,
    error = Error,
    onPrimary = ColorOnPrimary,
    onSecondary = OnSecondary
)


private val shapeScheme = Shapes(
    large = RoundedCornerShape(20.dp),
    medium = RoundedCornerShape(14.dp),
    small = RoundedCornerShape(8.dp)
)

@Composable
fun PokedexTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = darkColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colors = darkColorScheme,
        typography = Typography,
        content = content,
        shapes = shapeScheme
    )
}