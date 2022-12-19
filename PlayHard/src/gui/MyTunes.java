package gui;

import java.net.URL;
import java.util.Objects;

import dal.SongsDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MyTunes extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view/MyTunes.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("My Tunes");
        stage.centerOnScreen();
        stage.getIcons().add(new Image("https://img.icons8.com/external-yogi-aprelliyanto-basic-outline-yogi-aprelliyanto/512/external-music-note-multimedia-yogi-aprelliyanto-basic-outline-yogi-aprelliyanto.png"));
        stage.show();

    }
    public static void main(String[] args)
    {
        launch(args);
    }

}
