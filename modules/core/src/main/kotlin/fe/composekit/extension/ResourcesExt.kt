package fe.composekit.extension

import android.content.res.Configuration
import android.content.res.Resources

public fun Resources.isSystemDarkMode(): Boolean {
    return (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}
