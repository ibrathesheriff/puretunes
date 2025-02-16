package org.puretunes.ui;

import javafx.scene.media.MediaPlayer;

import javafx.scene.control.Slider;

public class VolumeSlider extends Slider {

    public VolumeSlider() {
        super(0, 1, 0.5); // Min: 0 (mute), Max: 1 (full volume), Default: 0.5
        this.setShowTickLabels(true);
        this.setShowTickMarks(true);
    }

    public void bindMediaPlayer(MediaPlayer mediaPlayer) {
        mediaPlayer.volumeProperty().bind(this.valueProperty());
    }
}
