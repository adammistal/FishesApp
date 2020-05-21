package pl.codeApp4u.fishesapp.data.remote.models;

import com.google.gson.annotations.SerializedName;

public class DictEntry {

    @SerializedName("id")
    private final Long id;

    @SerializedName("file")
    private final String file;

    @SerializedName("name")
    private final String name;

    public DictEntry(Long id, String file, String name) {
        this.id = id;
        this.file = file;
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
