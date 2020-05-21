package pl.codeApp4u.fishesapp.domain;

public class DictionaryItem {

    private final long id;
    private final String file;
    private final String name;

    public DictionaryItem(long id, String file, String name) {
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

    public long getId() {
        return id;
    }
}
