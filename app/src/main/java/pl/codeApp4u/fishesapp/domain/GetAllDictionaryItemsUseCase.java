package pl.codeApp4u.fishesapp.domain;

import java.util.List;

import io.reactivex.Observable;

public class GetAllDictionaryItemsUseCase {

    private final IAppRepository appRepository;

    public GetAllDictionaryItemsUseCase(IAppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public Observable<List<DictionaryItem>> execute() {
        return appRepository.getDictionaryItems();
    }
}
