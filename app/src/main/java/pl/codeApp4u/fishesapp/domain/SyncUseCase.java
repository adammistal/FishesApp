package pl.codeApp4u.fishesapp.domain;

import io.reactivex.Completable;

public class SyncUseCase {

    private final IAppRepository appRepository;

    public SyncUseCase(IAppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public Completable execute() {
        return appRepository.sync();
    }
}
