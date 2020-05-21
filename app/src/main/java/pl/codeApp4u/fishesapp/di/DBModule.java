package pl.codeApp4u.fishesapp.di;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.codeApp4u.fishesapp.App;
import pl.codeApp4u.fishesapp.data.db.AppDb;
import pl.codeApp4u.fishesapp.data.db.IEntriesDao;


@Module
public class DBModule {

    @Provides
    @Singleton
    AppDb getAppDb(App app) {
        return Room.databaseBuilder(app, AppDb.class, "app_db")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    IEntriesDao getEntriesDao(AppDb appDb) {
        return appDb.entriesDao();
    }

}
