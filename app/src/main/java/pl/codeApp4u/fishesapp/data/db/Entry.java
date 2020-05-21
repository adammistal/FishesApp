package pl.codeApp4u.fishesapp.data.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Entry {

    @PrimaryKey
    private final Long id;
    private final String file;
    private final String name;

    public Entry(Long id, String file, String name) {
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
