package flussonic.watcher.sample.presentation;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.squareup.leakcanary.LeakCanary;

import flussonic.watcher.sample.BuildConfig;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

@SuppressWarnings("WeakerAccess")
public class FlussonicWatcherSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        CrashlyticsCore crashlyticsCore = new CrashlyticsCore.Builder()
                .disabled(!BuildConfig.ENABLE_CRASHLYTICS)
                .build();
        Crashlytics crashlytics = new Crashlytics.Builder()
                .core(crashlyticsCore)
                .build();
        Fabric.with(this, crashlytics);

        Timber.plant(new LoggingTree());
    }
}
