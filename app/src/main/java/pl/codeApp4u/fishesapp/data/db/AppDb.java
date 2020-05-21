package pl.codeApp4u.fishesapp.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = Entry.class,
        version = 1,
        exportSchema = false
)
abstract public class AppDb extends RoomDatabase {

    public abstract IEntriesDao entriesDao();

}
