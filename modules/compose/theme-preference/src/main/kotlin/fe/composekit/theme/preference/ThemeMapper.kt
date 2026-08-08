package fe.composekit.theme.preference

import fe.android.preference.helper.OptionTypeMapper
import fe.composekit.theme.Theme


public object ThemeMapper : OptionTypeMapper<Theme, String>(
    key = { it.name },
    options = { arrayOf(Theme.System, Theme.Light, Theme.Dark) }
)
