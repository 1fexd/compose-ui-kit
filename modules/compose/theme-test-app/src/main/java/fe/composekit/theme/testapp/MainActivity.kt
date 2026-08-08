package fe.composekit.theme.testapp

import android.os.Bundle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fe.composekit.appbase.AppBaseComponentActivity
import fe.composekit.appbase.AppTheme
import fe.composekit.theme.Theme
import fe.composekit.theme.preference.PreferenceTheme2
import fe.composekit.theme.testapp.preference.app.AppPreferenceRepository
import fe.composekit.theme.testapp.theme.AppColor
import fe.composekit.theme.testapp.theme.Typography
import org.koin.android.ext.android.inject

class MainActivity : AppBaseComponentActivity() {
    private val viewModel by inject<MainViewModel>()
//    private val viewModel by inject<StateMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent(edgeToEdge = true) {
            PreferenceTheme2(themeHolder = viewModel) { theme, themeMaterialYou, themeAmoled ->
                AppTheme(
                    appColor = AppColor,
                    typography = Typography,
                    theme = theme,
                    materialYou = themeMaterialYou,
                    amoled = themeAmoled
                ) {
                    Surface {
                        Column(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
                            Text(text = "Color background: ${MaterialTheme.colorScheme.background.value}")
                            Text(text = "Current theme: $theme")
                            Text(text = "Material you: $themeMaterialYou")
                            Text(text = "Amoled: $themeAmoled")
                            Text(text = "System dark theme: ${isSystemInDarkTheme()}")

                            Row {
                                Button(onClick = {
                                    viewModel.theme(Theme.System)
                                }) {
                                    Text(text = "System")
                                }
                                Button(onClick = {
                                    viewModel.theme(Theme.Dark)
                                }) {
                                    Text(text = "Dark")
                                }
                                Button(onClick = {
                                    viewModel.theme(Theme.Light)
                                }) {
                                    Text(text = "Light")
                                }
                            }
                        }
                    }
               }
            }
        }
    }
}
