package fe.composekit.theme.testapp

import androidx.lifecycle.ViewModel
import fe.composekit.theme.preference.StateThemeHolder
import fe.composekit.theme.preference.ThemeHolder
import fe.composekit.theme.preference.ThemePreferences
import fe.composekit.theme.testapp.preference.app.AppPreferenceRepository
import fe.composekit.theme.testapp.preference.app.StateAppPreferenceRepository

class MainViewModel(
    private val repository: AppPreferenceRepository
) : ViewModel(), ThemeHolder {
    override val theme = repository.asViewModelState(ThemePreferences.theme)
    override val themeAmoled = repository.asViewModelState(ThemePreferences.themeAmoled)
    override val themeMaterialYou = repository.asViewModelState(ThemePreferences.themeMaterialYou)
}

class StateMainViewModel(
    private val repository: StateAppPreferenceRepository
) : ViewModel(), StateThemeHolder {
    override val theme = repository.asState(ThemePreferences.theme)
    override val themeAmoled = repository.asState(ThemePreferences.themeAmoled)
    override val themeMaterialYou = repository.asState(ThemePreferences.themeMaterialYou)
}
