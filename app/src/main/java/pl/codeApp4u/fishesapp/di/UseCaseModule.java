package pl.codeApp4u.fishesapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.codeApp4u.fishesapp.domain.GetAllDictionaryItemsUseCase;
import pl.codeApp4u.fishesapp.domain.IAppRepository;
import pl.codeApp4u.fishesapp.domain.SyncUseCase;


@Module
public class UseCaseModule {

    @Provides
    @Singleton
    SyncUseCase getSyncUseCase(IAppRepository repository) {
        return new SyncUseCase(repository);
    }

    @Provides
    @Singleton
    GetAllDictionaryItemsUseCase getGetAllDictionaryItemsUseCase(IAppRepository repository) {
        return new GetAllDictionaryItemsUseCase(repository);
    }
}
