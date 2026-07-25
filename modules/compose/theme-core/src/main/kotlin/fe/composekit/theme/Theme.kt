package fe.composekit.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.ui.graphics.Color

public sealed class Theme(public val name: String) {
    public data object System : Theme("system")
    public data object Light : Theme("light")
    public data object Dark : Theme("dark")

    public fun getColorScheme(
        context: Context,
        defaultAppColor: AppColorScheme,
        systemDarkTheme: Boolean,
        materialYou: Boolean,
        amoled: Boolean,
    ): ColorScheme {
        if (this is System) {
            val theme = if (systemDarkTheme) Dark else Light
            return theme.getColorScheme(context, defaultAppColor, systemDarkTheme, materialYou, amoled)
        }

        val useMaterialYou = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && materialYou

        if (this is Dark) {
            val scheme = if (useMaterialYou) dynamicDarkColorScheme(context) else defaultAppColor.darkColors
            return if (amoled) scheme.copy(surface = Color.Black, background = Color.Black) else scheme
        }

        return if (useMaterialYou) dynamicLightColorScheme(context) else defaultAppColor.lightColors
    }
}
