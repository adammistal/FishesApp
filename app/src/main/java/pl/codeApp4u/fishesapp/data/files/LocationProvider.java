package pl.codeApp4u.fishesapp.data.files;

import java.io.File;

import javax.inject.Inject;

import pl.codeApp4u.fishesapp.App;

public class LocationProvider implements ILocationProvider {

    private final String location;

    @Inject
    public LocationProvider(App app) {
        location = app.getFilesDir() + File.separator;
    }

    public String getLocation() {
        return location;
    }
}
