package fe.composekit.theme

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.Immutable
import fe.composekit.extension.isSystemDarkMode

@Immutable
public data class ThemeConfig(
    val theme: Theme,
    val materialYou: Boolean,
    val amoled: Boolean,
)

public fun Theme.isDarkTheme(resources: Resources): Boolean {
    val systemDarkTheme = resources.isSystemDarkMode()
    val darkTheme = isDarkTheme(systemDarkTheme)
    Log.d("ThemeConfig", "darkTheme=$darkTheme")

    return darkTheme
}

public fun Theme.isDarkTheme(systemDarkTheme: Boolean): Boolean {
    return this == Theme.Dark || (this == Theme.System && systemDarkTheme)
}
