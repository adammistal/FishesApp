package pl.codeApp4u.fishesapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.codeApp4u.fishesapp.ui.MainActivity;

@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = UIModule.class)
    abstract MainActivity contributeMainActivity();
}


