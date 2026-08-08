package fe.composekit.theme.preference

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import fe.android.preference.helper.Preference
import fe.android.preference.helper.compose.StatePreferenceRepository
import fe.composekit.preference.collectAsStateWithLifecycle
import fe.composekit.theme.Theme
import fe.composekit.theme.ThemeConfig


@Composable
public fun PreferenceTheme(
    preferences: StatePreferenceRepository,
    themePref: Preference.Mapped<Theme, String> = ThemePreferences.theme,
    materialYouPref: Preference.Boolean = ThemePreferences.themeMaterialYou,
    amoledPref: Preference.Boolean = ThemePreferences.themeAmoled,
    content: @Composable (ThemeConfig) -> Unit,
) {
    val theme by remember { preferences.asState(themePref) }
    val themeMaterialYou by remember { preferences.asState(materialYouPref) }
    val themeAmoled by remember { preferences.asState(amoledPref) }

    content(ThemeConfig(theme, themeMaterialYou, themeAmoled))
}

@Composable
public fun PreferenceTheme(
    preferences: StatePreferenceRepository,
    themePref: Preference.Mapped<Theme, String> = ThemePreferences.theme,
    materialYouPref: Preference.Boolean = ThemePreferences.themeMaterialYou,
    amoledPref: Preference.Boolean = ThemePreferences.themeAmoled,
    content: @Composable (theme: Theme, themeMaterialYou: Boolean, themeAmoled: Boolean) -> Unit,
) {
    val theme by remember { preferences.asState(themePref) }
    val themeMaterialYou by remember { preferences.asState(materialYouPref) }
    val themeAmoled by remember { preferences.asState(amoledPref) }

    content(theme, themeMaterialYou, themeAmoled)
}

@Composable
public fun PreferenceTheme2(
    themeHolder: StateThemeHolder,
    content: @Composable (theme: Theme, themeMaterialYou: Boolean, themeAmoled: Boolean) -> Unit,
) {
    content(themeHolder.theme(), themeHolder.themeMaterialYou(), themeHolder.themeAmoled())
}

@Composable
public fun PreferenceTheme2(
    themeHolder: ThemeHolder,
//    content: @Composable (ThemeConfig) -> Unit,
    content: @Composable (theme: Theme, themeMaterialYou: Boolean, themeAmoled: Boolean) -> Unit,
) {
    val theme by themeHolder.theme.collectAsStateWithLifecycle()
    val themeMaterialYou by themeHolder.themeMaterialYou.collectAsStateWithLifecycle()
    val themeAmoled by themeHolder.themeAmoled.collectAsStateWithLifecycle()

    content(theme, themeMaterialYou, themeAmoled)
}
