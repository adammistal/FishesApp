package pl.codeApp4u.fishesapp.di;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import pl.codeApp4u.fishesapp.ui.content.ContentFragment;
import pl.codeApp4u.fishesapp.ui.content.ContentViewModel;

@Module
public abstract class UIModule {

    @ContributesAndroidInjector
    abstract ContentFragment contributeContentFragment();

    @Binds
    @IntoMap
    @ViewModelKey(ContentViewModel.class)
    abstract ViewModel bindContentViewModel(ContentViewModel viewModel);
}
