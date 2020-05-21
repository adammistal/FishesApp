package pl.codeApp4u.fishesapp.ui.content;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import pl.codeApp4u.fishesapp.domain.DictionaryItem;
import pl.codeApp4u.fishesapp.domain.GetAllDictionaryItemsUseCase;
import pl.codeApp4u.fishesapp.domain.SyncUseCase;

public class ContentViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final SyncUseCase syncUseCase;
    private final GetAllDictionaryItemsUseCase getAllDictionaryItemsUseCase;
    private final MutableLiveData<List<DictionaryItem>> mItems = new MutableLiveData<>();
    public final LiveData<List<DictionaryItem>> items = mItems;

    @Inject
    public ContentViewModel(SyncUseCase syncUseCase, GetAllDictionaryItemsUseCase getAllDictionaryItemsUseCase) {
        this.syncUseCase = syncUseCase;
        this.getAllDictionaryItemsUseCase = getAllDictionaryItemsUseCase;
        compositeDisposable.add(
                getAllDictionaryItemsUseCase.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::success, this::failure));
    }

    private void failure(Throwable throwable) {
        Log.e("Error:", "getAllDictionaryItemsUseCase: " + throwable);
    }

    private void success(List<DictionaryItem> dictionaryItems) {
        mItems.setValue(dictionaryItems);
    }

    public void sync() {
        compositeDisposable.add(
                syncUseCase.execute()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::successSync, this::failureSync));
    }

    private void failureSync(Throwable throwable) {
        Log.e("Error:", "syncUseCase: " + throwable);
    }

    private void successSync() {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
