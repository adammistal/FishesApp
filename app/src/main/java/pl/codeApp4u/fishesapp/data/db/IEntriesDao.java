package pl.codeApp4u.fishesapp.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface IEntriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(List<Entry> entries);

    @Update
    Completable update(List<Entry> entries);

    @Query("SELECT * FROM Entry")
    Observable<List<Entry>> getAll();

    @Query("SELECT * FROM Entry WHERE entry.id = :id")
    Single<Entry> getEntryWithId(long id);

    @Query("DELETE FROM Entry WHERE entry.id = :id")
    Completable deleteEntry(long id);

}
