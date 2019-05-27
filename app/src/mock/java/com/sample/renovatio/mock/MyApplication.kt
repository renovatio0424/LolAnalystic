import android.annotation.SuppressLint
import android.app.Application
import com.sample.renovatio.mock.di.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@SuppressLint("Registered")
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(myDiModule)
        }
    }
}