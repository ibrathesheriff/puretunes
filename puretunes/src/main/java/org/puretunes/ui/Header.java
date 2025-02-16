package org.puretunes.ui;

import org.puretunes.data.MusicQueue;
import org.puretunes.data.Song;

import javafx.scene.layout.FlowPane;

public class Header extends FlowPane {
    private PlayButtons playButtons;
    private VolumeSlider volumeSlider;

    private MusicQueue<Song> musicQueue;

    public Header(MusicQueue<Song> musicQueue) {
        super();
        this.musicQueue = musicQueue;
        init();
    }

    private void init() {
        volumeSlider = new VolumeSlider();
        playButtons = new PlayButtons(musicQueue, volumeSlider);
        this.getChildren().addAll(playButtons, volumeSlider); 
    }
}
