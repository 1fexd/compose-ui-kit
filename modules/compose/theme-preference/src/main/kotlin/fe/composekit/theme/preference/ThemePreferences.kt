package fe.composekit.theme.preference

import fe.android.preference.helper.Preference
import fe.android.preference.helper.PreferenceDefinition
import fe.composekit.theme.Theme


public object ThemePreferences : PreferenceDefinition() {
    public val theme: Preference.Mapped<Theme, String> = mapped("theme", Theme.System, ThemeMapper)
    public val themeMaterialYou: Preference.Boolean = boolean("theme_material_you", true)
    public val themeAmoled: Preference.Boolean = boolean("theme_amoled_enabled")

    init {
        finalize()
    }
}
