package gui.controller;

import gui.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyTunesController implements Initializable {

    private ArrayList<Stage> listOfStages = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addSong() throws IOException {
        System.out.println("addSongClick");
        openAddSong();
    }
    public void editSong() throws IOException {
        System.out.println("editSongClick");
        openEditSong();
    }
    public void addPlaylist() throws IOException {
        openAddPlaylist();
    }

    public void editPlaylist() throws IOException {
        openEditPlaylist();
    }
    public void openAddSong() throws IOException{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/newSong.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddSong = new Stage();
        listOfStages.add(stageAddSong);
        stageAddSong.setTitle("Add a song");
        stageAddSong.setScene(scene);
        stageAddSong.show();
    }
    public void openEditSong() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/editSong.fxml"));
        Scene scene = new Scene(loader.load());
        MyTunesController gameCon = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Edit a song");
        stage.setScene(scene);
        stage.show();
    }
    public void openAddPlaylist() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/newPlaylist.fxml"));
        Scene scene = new Scene(loader.load());
        MyTunesController gameCon = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Add a playlist");
        stage.setScene(scene);
        stage.show();
    }

    public void openEditPlaylist() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/editPlaylist.fxml"));
        Scene scene = new Scene(loader.load());
        MyTunesController gameCon = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Edit a song");
        stage.setScene(scene);
        stage.show();
    }


}
