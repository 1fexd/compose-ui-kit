package fe.composekit.theme.testapp.preference.app

import android.content.Context
import fe.android.preference.helper.compose.StatePreferenceRepository
import fe.composekit.preference.FlowPreferenceRepository
import fe.composekit.theme.preference.ThemePreferences

class AppPreferenceRepository(context: Context) : FlowPreferenceRepository(context) {

    init {
        AppPreferences.runMigrations(this@AppPreferenceRepository)
        ThemePreferences.runMigrations(this@AppPreferenceRepository)
    }
}
class StateAppPreferenceRepository(context: Context) : StatePreferenceRepository(context) {

    init {
        AppPreferences.runMigrations(this@StateAppPreferenceRepository)
        ThemePreferences.runMigrations(this@StateAppPreferenceRepository)
    }
}
