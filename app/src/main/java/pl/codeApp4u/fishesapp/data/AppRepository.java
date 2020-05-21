package pl.codeApp4u.fishesapp.data;

import android.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import pl.codeApp4u.fishesapp.data.db.Entry;
import pl.codeApp4u.fishesapp.data.db.IEntriesDao;
import pl.codeApp4u.fishesapp.data.remote.IDownloadService;
import pl.codeApp4u.fishesapp.data.remote.models.DictEntry;
import pl.codeApp4u.fishesapp.data.remote.models.IndexResponse;
import pl.codeApp4u.fishesapp.data.zip.IUnzip;
import pl.codeApp4u.fishesapp.domain.DictionaryItem;
import pl.codeApp4u.fishesapp.domain.IAppRepository;

public class AppRepository implements IAppRepository {

    private final IDownloadService downloadService;
    private final IUnzip unzip;
    private final IEntriesDao entriesDao;

    @Inject
    public AppRepository(IDownloadService downloadService, IUnzip unzip, IEntriesDao entriesDao) {
        this.downloadService = downloadService;
        this.unzip = unzip;
        this.entriesDao = entriesDao;
    }

    @Override
    public Completable sync() {
        return downloadService.getIndex()
                .flatMap(this::downloadFile)
                .flatMapCompletable(this::updateEntriesDao);
    }

    private Completable updateEntriesDao(Pair<String, List<DictEntry>> input) {
        return entriesDao.insert(input.second
                .stream()
                .map(data -> mapToEntry(data, input.first))
                .collect(Collectors.toList()));
    }

    private Entry mapToEntry(DictEntry de, String location) {
        return new Entry(de.getId(), location + de.getFile(), de.getName());
    }

    private Single<Pair<String, List<DictEntry>>> downloadFile(IndexResponse indexResponse) {
        return downloadService.downloadZip(indexResponse.getZipUrl())
                .flatMap(unzip::unzip)
                .flatMap(data -> Single.just(new Pair<>(data, indexResponse.getEntries())));
    }

    @Override
    public Observable<List<DictionaryItem>> getDictionaryItems() {
        return entriesDao.getAll()
                .map(this::mapToDictionaryItem);
    }

    private List<DictionaryItem> mapToDictionaryItem(List<Entry> entries) {
        return entries
                .stream()
                .map(this::mapToEntry)
                .collect(Collectors.toList());
    }

    private DictionaryItem mapToEntry(Entry de) {
        return new DictionaryItem(de.getId(), de.getFile(), de.getName());
    }
}
