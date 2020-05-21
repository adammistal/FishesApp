package pl.codeApp4u.fishesapp.data.remote;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import pl.codeApp4u.fishesapp.data.remote.models.IndexResponse;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface IDownloadService {

    @GET("index.json")
    Single<IndexResponse> getIndex();

    @GET
    Single<ResponseBody> downloadZip(@Url String fileUrl);
}
