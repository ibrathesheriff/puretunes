package org.puretunes.data;

import java.io.File;

public class Song {
    private final String filePath;

    public Song(String filePath) {
        this.filePath = new File(filePath).toURI().toString();
    }

    public String getFilePath() {
        return filePath;
    }
}
