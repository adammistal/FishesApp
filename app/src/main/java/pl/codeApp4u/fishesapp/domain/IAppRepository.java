package pl.codeApp4u.fishesapp.domain;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface IAppRepository {

    Completable sync();

    Observable<List<DictionaryItem>> getDictionaryItems();
}
