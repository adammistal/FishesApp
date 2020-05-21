package pl.codeApp4u.fishesapp.data.zip;

import java.io.File;

import io.reactivex.SingleSource;
import okhttp3.ResponseBody;

public interface IUnzip {

    SingleSource<String> unzip(ResponseBody body);
}
