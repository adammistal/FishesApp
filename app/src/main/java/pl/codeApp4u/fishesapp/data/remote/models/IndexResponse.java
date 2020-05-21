package pl.codeApp4u.fishesapp.data.remote.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndexResponse {

    @SerializedName("zipUrl")
    private final String zipUrl;
    @SerializedName("entries")
    private final List<DictEntry> entries;

    public IndexResponse(String zipUrl, List<DictEntry> entries) {
        this.zipUrl = zipUrl;
        this.entries = entries;
    }

    public String getZipUrl() {
        return zipUrl;
    }

    public List<DictEntry> getEntries() {
        return entries;
    }
}
