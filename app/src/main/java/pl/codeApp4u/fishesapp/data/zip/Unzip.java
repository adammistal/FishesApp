package pl.codeApp4u.fishesapp.data.zip;

import android.util.Log;

import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.LocalFileHeader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import okhttp3.ResponseBody;
import pl.codeApp4u.fishesapp.data.files.ILocationProvider;

public class Unzip implements IUnzip {

    private static final char[] PASSWORD = "123456".toCharArray();

    private final String location;

    @Inject
    public Unzip(ILocationProvider locationProvider) {
        location = locationProvider.getLocation();
    }

    private void _dirChecker(String location, String dir) {
        File f = new File(location + dir);

        if (!f.isDirectory()) {
            f.mkdirs();
        }
    }

    public SingleSource<String> unzip(ResponseBody body) {
        try {

            InputStream fin = null;
            ZipInputStream zin = null;
            try {
                Log.v("Decompress", "Location " + location);
                fin = body.byteStream();
                zin = new ZipInputStream(fin, PASSWORD);

                byte[] buffer = new byte[8192];

                LocalFileHeader ze;
                while ((ze = zin.getNextEntry()) != null) {
                    Log.v("Decompress", "Unzipping " + ze.getFileName());

                    if (ze.isDirectory()) {
                        _dirChecker(location, ze.getFileName());
                    } else {
                        String filePath = location + ze.getFileName();
                        Log.v("Decompress", "filePath " + filePath);
                        FileOutputStream fout = new FileOutputStream(filePath);

                        BufferedInputStream in = new BufferedInputStream(zin);
                        BufferedOutputStream out = new BufferedOutputStream(fout);

                        int n;
                        while ((n = in.read(buffer, 0, 8192)) >= 0) {
                            out.write(buffer, 0, n);
                        }
                        out.close();
                    }

                }
                zin.close();
                return Single.just(location);
            } catch (Exception e) {
                Log.e("Decompress", "unzip", e);
                return Single.error(e);
            } finally {
                if (fin != null) {
                    fin.close();
                }

                if (zin != null) {
                    zin.close();
                }
            }
        } catch (IOException e) {
            return Single.error(e);
        }
    }
}
