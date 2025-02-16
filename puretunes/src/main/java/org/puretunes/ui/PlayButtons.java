package org.puretunes.ui;

import java.io.File;

import org.puretunes.data.MusicQueue;
import org.puretunes.data.Song;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayButtons extends FlowPane implements EventHandler<ActionEvent> {
    private Button backButton;
    private Button playPauseButton;
    private Button nextButton;
    private MusicQueue<Song> musicQueue;
    private VolumeSlider volumeSlider;

    private MediaPlayer mediaPlayer;
    private boolean musicPlaying;

    public PlayButtons(MusicQueue<Song> musicQueue, VolumeSlider volumeSlider) {
        super();
        this.volumeSlider = volumeSlider;
        this.musicQueue = musicQueue;
        musicPlaying = false;
        mediaPlayer = null;
        init();
    }

    private void init() {
        backButton = new Button("back");
        backButton.setOnAction(this);
        
        playPauseButton = new Button("play/pause");
        playPauseButton.setOnAction(this);

        nextButton = new Button("next");
        nextButton.setOnAction(this);
        this.getChildren().addAll(backButton, playPauseButton, nextButton);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getPlayPauseButton() {
        return playPauseButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    private MediaPlayer createMediaPlayer() {
        Song song = musicQueue.dequeue();
        Media media = new Media(song.getFilePath());
        mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == playPauseButton) {
            if (mediaPlayer == null) {
                // starting a new music session
                mediaPlayer = createMediaPlayer();
                volumeSlider.bindMediaPlayer(mediaPlayer);
                mediaPlayer.setOnEndOfMedia(() -> playNextSong());
                mediaPlayer.play();
            } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play();
            } else {
                mediaPlayer.pause();
            }
        } else if (event.getSource() == nextButton) {
            playNextSong();
        }
    }

    private void playNextSong() {
        stopMediaPlayer();
        mediaPlayer = createMediaPlayer();
        volumeSlider.bindMediaPlayer(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> playNextSong());
        mediaPlayer.play();
    }

    private void stopMediaPlayer() {
        mediaPlayer.stop();
        mediaPlayer.dispose();
    }

}
