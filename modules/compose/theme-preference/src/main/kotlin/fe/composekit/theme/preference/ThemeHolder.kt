package fe.composekit.theme.preference

import fe.android.preference.helper.Preference
import fe.android.preference.helper.compose.StateMappedPreference
import fe.android.preference.helper.compose.StatePreference
import fe.composekit.preference.ViewModelStatePreference
import fe.composekit.theme.Theme

public interface ThemeHolder {
    public val theme: ViewModelStatePreference<Theme, Theme, Preference.Mapped<Theme, String>>
    public val themeAmoled: ViewModelStatePreference<Boolean, Boolean, Preference.Default<Boolean>>
    public val themeMaterialYou: ViewModelStatePreference<Boolean, Boolean, Preference.Default<Boolean>>
}

public interface StateThemeHolder {
    public val theme: StateMappedPreference<Theme, String>
    public val themeAmoled: StatePreference<Boolean>
    public val themeMaterialYou: StatePreference<Boolean>
}
