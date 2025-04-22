package org.puretunes.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

import org.puretunes.data.DBConnect;
import org.puretunes.data.MusicQueue;
import org.puretunes.data.SongCrawler;

import org.puretunes.data.Song;
import org.puretunes.layouts.TwoColumnLayout;
import org.puretunes.ui.Header;

/**
 * JavaFX App
 */
public class App extends Application {
    private Header header;
    private Scene musicScene;

    private MusicQueue<Song> musicQueue;

    @Override
    public void start(Stage stage) {
        createmusicScene();
        stage.setScene(musicScene);
        stage.show();
    }

    private static final String ALESSIA_TRACK = "C:\\Users\\ibrathesheriff\\Music\\alessia_cara\\Alessia Cara - Overdose (Audio).mp3";
    private static final String DUA_LIPA_TRACK = "C:\\Users\\ibrathesheriff\\Music\\dua_lipa\\Dua Lipa & BLACKPINK - Kiss and Make Up (Official Audio).mp3";
    private static final String NF_TRACK = "C:\\Users\\ibrathesheriff\\Music\\f_why_mp3_60568.mp3.mp3";

    private void createmusicScene() {
        musicQueue = new MusicQueue<>();
        //musicQueue.enqueue(new Song(ALESSIA_TRACK));
        //musicQueue.enqueue(new Song(DUA_LIPA_TRACK));
        //musicQueue.enqueue(new Song(NF_TRACK));
        SongCrawler.loadMusicQueue(musicQueue, true);

        header = new Header(musicQueue);

        BorderPane root = new BorderPane();

        // HEADER (TOP)
        root.setTop(header);

        // TWO COLUMNS (CENTER)
        TwoColumnLayout twoColumnLayout = new TwoColumnLayout(30, 70);

        // Left Column (30%)
        StackPane leftColumn = new StackPane();
        leftColumn.setStyle("-fx-background-color: #3498db;");
        leftColumn.getChildren().add(new Label("Left Column"));

        // Right Column (70%)
        StackPane rightColumn = new StackPane();
        rightColumn.setStyle("-fx-background-color: #2ecc71;");
        rightColumn.getChildren().add(new Label("Right Column"));

        // Column Constraints (30% left, 70% right)
        twoColumnLayout.addColumns(leftColumn, rightColumn);

        root.setCenter(twoColumnLayout);

        musicScene = new Scene(root, 800, 400);

        //DBConnect dbConnect = new DBConnect();
        try {
            Connection conn = DBConnect.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}