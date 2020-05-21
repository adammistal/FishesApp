package pl.codeApp4u.fishesapp;

import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import pl.codeApp4u.fishesapp.di.AppComponent;
import pl.codeApp4u.fishesapp.di.DaggerAppComponent;

public class App extends Application implements HasAndroidInjector {

    @Inject
    protected DispatchingAndroidInjector<Object> androidInjector;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
        appComponent.inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
