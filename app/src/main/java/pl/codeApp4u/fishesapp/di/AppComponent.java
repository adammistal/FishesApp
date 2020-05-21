package pl.codeApp4u.fishesapp.di;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import pl.codeApp4u.fishesapp.App;

@Singleton
@Component(
        modules = {AppModule.class, NetworkModule.class,
                AndroidSupportInjectionModule.class,
                AndroidInjectionModule.class,
                UseCaseModule.class,
                MainActivityModule.class,
                RepositoryModule.class,
                DBModule.class}
)
public interface AppComponent {
    @Component.Builder
    interface Builder {

        AppComponent build();

        @BindsInstance
        Builder application(App application);
    }

    void inject(App application);
}
