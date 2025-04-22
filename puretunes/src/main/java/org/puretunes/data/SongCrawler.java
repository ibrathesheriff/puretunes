package org.puretunes.data;

import java.io.File;
import java.util.ArrayList;

public class SongCrawler {
    private static final String MUSIC_DIRECTORY = "C:\\Users\\ibrathesheriff\\Music";

    public static void loadMusicQueue(MusicQueue<Song> musicQueue, boolean shouldShuffle) {
        ArrayList<String> songList = new ArrayList<>();
        loadSongs(songList, MUSIC_DIRECTORY);
        if (shouldShuffle) {
            shuffleList(songList);
        }
        for (String songPath: songList) {
            musicQueue.enqueue(new Song(songPath));
        }
    }

    public static void loadSongs(ArrayList<String> songList, String folderPath) {
        File directoryPath = new File(folderPath);
        //List of all files and directories
        File filesList[] = directoryPath.listFiles();
        for (File file: filesList) {
            if (file.isDirectory()) {
                loadSongs(songList, file.getAbsolutePath());
            } else {
                String tempPath = file.getAbsolutePath();
                if (tempPath.endsWith(".mp3")) {
                    songList.add(tempPath);
                    System.out.println(tempPath);
                }
            }
        }
    }

    public static void shuffleList(ArrayList<String> songList) {
        int n = songList.size();
        for (int i = 0; i < n; i++) {
            int r = getRandomNumber(i, n);
            String temp = songList.get(i);
            songList.set(i, songList.get(r));
            songList.set(r, temp);
        }
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
