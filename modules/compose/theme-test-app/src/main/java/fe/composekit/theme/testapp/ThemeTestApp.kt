package fe.composekit.theme.testapp

import android.app.Application
import fe.composekit.theme.testapp.preference.app.AppPreferenceRepository
import fe.composekit.theme.testapp.preference.app.StateAppPreferenceRepository
import fe.droidkit.koin.androidApplicationContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class ThemeTestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidApplicationContext<ThemeTestApp>(this@ThemeTestApp)
            modules(
                module {
                    singleOf(::AppPreferenceRepository)
                    singleOf(::StateAppPreferenceRepository)
                    viewModelOf(::MainViewModel)
                    viewModelOf(::StateMainViewModel)
                }
            )
        }
    }
}
