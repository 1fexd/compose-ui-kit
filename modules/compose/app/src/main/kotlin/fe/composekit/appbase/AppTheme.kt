package fe.composekit.appbase

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import fe.composekit.theme.AppColorScheme
import fe.composekit.theme.Theme
import fe.composekit.theme.ThemeConfig
import fe.composekit.theme.isDarkTheme


public tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

//val LocalActivity = staticCompositionLocalOf<Activity> { error("CompositionLocal LocalActivity not present") }


/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

public val darkSystemBars: SystemBarHolder = SystemBarHolder(
    statusBar = SystemBarStyle.dark(Color.TRANSPARENT),
    navigationBar = SystemBarStyle.dark(darkScrim)
)
public val lightSystemBars: SystemBarHolder = SystemBarHolder(
    statusBar = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
    navigationBar = SystemBarStyle.light(lightScrim, darkScrim)
)

@Immutable
public data class SystemBarHolder(
    val statusBar: SystemBarStyle,
    val navigationBar: SystemBarStyle
)

@Composable
public fun AppBaseComponentActivity.AppTheme(
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColorScheme,
    typography: Typography,
    config: ThemeConfig,
    content: @Composable () -> Unit,
) {
    AppTheme(
        edgeToEdge = edgeToEdge,
        systemDarkTheme = systemDarkTheme,
        appColor = appColor,
        typography = typography,
        config = config,
        updateEdgeToEdge = ::updateEdgeToEdge,
        content = content
    )
}


@Composable
public fun AppBaseComponentActivity.AppTheme(
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColorScheme,
    typography: Typography,
    theme: Theme,
    materialYou: Boolean,
    amoled: Boolean,
    content: @Composable () -> Unit,
) {
    AppTheme(
        edgeToEdge = edgeToEdge,
        systemDarkTheme = systemDarkTheme,
        appColor = appColor,
        typography = typography,
        theme = theme,
        materialYou = materialYou,
        amoled = amoled,
        updateEdgeToEdge = ::updateEdgeToEdge,
        content = content
    )
}

@Composable
public fun AppTheme(
    edgeToEdge: Boolean = true,
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColorScheme,
    typography: Typography,
    config: ThemeConfig,
    updateEdgeToEdge: ((SystemBarHolder) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    AppTheme(
        edgeToEdge = edgeToEdge,
        systemDarkTheme = systemDarkTheme,
        appColor = appColor,
        typography = typography,
        theme = config.theme,
        materialYou = config.materialYou,
        amoled = config.amoled,
//        config = ThemeConfig(theme, materialYou, amoled),
        updateEdgeToEdge = updateEdgeToEdge,
        content = content
    )
}

@Composable
public fun AppTheme(
    edgeToEdge: Boolean = true,
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColorScheme,
    typography: Typography,
    theme: Theme,
    materialYou: Boolean,
    amoled: Boolean,
    updateEdgeToEdge: ((SystemBarHolder) -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme = theme.getColorScheme(
        context,
        appColor,
        systemDarkTheme,
        materialYou,
        amoled
    )

    if (edgeToEdge && updateEdgeToEdge != null) {
        LaunchedEffect(key1 = theme, key2 = systemDarkTheme) {
//            val isDarkMode: (Resources) -> Boolean = { resources -> theme.isDarkTheme(resources) }
            val isDarkMode = theme.isDarkTheme(systemDarkTheme)
//            Log.d("AppTheme", "AppThemeInternal(theme=$theme, systemDarkTheme=$systemDarkTheme)")
            updateEdgeToEdge(if (isDarkMode) darkSystemBars else lightSystemBars)
        }
    }

    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

@Composable
public fun AppBaseComponentActivity.BoxAppHost(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    defaultAppColor: AppColorScheme,
    typography: Typography,
    config: ThemeConfig,
    content: @Composable BoxScope.() -> Unit,
) {
    AppTheme(
        appColor = defaultAppColor,
        typography = typography,
        config = config
    ) {
        Box(modifier = modifier, contentAlignment = contentAlignment, content = content)
    }
}
