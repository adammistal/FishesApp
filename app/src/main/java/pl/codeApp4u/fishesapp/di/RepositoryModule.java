package pl.codeApp4u.fishesapp.di;


import dagger.Binds;
import dagger.Module;
import pl.codeApp4u.fishesapp.data.AppRepository;
import pl.codeApp4u.fishesapp.data.files.ILocationProvider;
import pl.codeApp4u.fishesapp.data.files.LocationProvider;
import pl.codeApp4u.fishesapp.data.zip.IUnzip;
import pl.codeApp4u.fishesapp.data.zip.Unzip;
import pl.codeApp4u.fishesapp.domain.IAppRepository;

@Module
public abstract class RepositoryModule {


    @Binds
    abstract ILocationProvider bindLocationProvider(LocationProvider instance);

    @Binds
    abstract IAppRepository bindAppRepository(AppRepository instance);

    @Binds
    abstract IUnzip bindZipDecompress(Unzip instance);

}
