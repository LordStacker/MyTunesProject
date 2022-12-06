package gui.controller;

import be.Songs;
import dal.SongsDAO;
import gui.MyTunes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {
    //table columns
    public TableView<Songs> songsTable;
    public TableColumn<Songs, String> titleColumn;
    public TableColumn<Songs, String> artistColumn;
    public TableColumn<Songs, String> categoryColumn;
    public TableColumn<Songs, Integer> timeColumn;

    private ArrayList<Stage> listOfStages = new ArrayList<>();

    private SongsDAO SongsDAO = new SongsDAO();

    public Slider volumeSlider;

    private javafx.scene.media.Media media;

    private  boolean running;

    private int songId;

    @FXML
    public Label songLabel;

    public MediaPlayer mediaPlayer;

    @FXML
    private Button playBtn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //table view

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
        songsTable.setItems(SongsDAO.getAllSongs());


        //Playing Music
        media = new Media(SongsDAO.setMedia(songId));
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(SongsDAO.getNameSong(songId));

        if(mediaPlayer != null){
            volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
                }
            });
        }


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
        FXMLLoader loader = new FXMLLoader(MyTunes.class.getResource("view/newSong.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddSong = new Stage();
        listOfStages.add(stageAddSong);
        stageAddSong.setTitle("Add a song");



        stageAddSong.setScene(scene);
        stageAddSong.show();
        stageAddSong.setResizable(false);
    }
    public void openEditSong() throws IOException {
        FXMLLoader loader = new FXMLLoader(MyTunes.class.getResource("view/editSong.fxml"));
        Scene scene = new Scene(loader.load());
        MyTunesController gameCon = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("Edit a song");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public void openAddPlaylist() throws IOException {
        FXMLLoader loader = new FXMLLoader(MyTunes.class.getResource("view/newPlaylist.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Add a playlist");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    public void openEditPlaylist() throws IOException {
        FXMLLoader loader = new FXMLLoader(MyTunes.class.getResource("view/editPlaylist.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = new Stage();
        stage.setTitle("Edit a song");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public void playMedia(ActionEvent actionEvent) {

        if(running == false){
            mediaPlayer.play();
            playBtn.setText("⏸");
            running=true;
        }
        else {
            mediaPlayer.pause();
            playBtn.setText("▶");
            running=false;
        }
    }

    public void nextMedia(ActionEvent actionEvent) {
        if (songId < SongsDAO.getSongList().size() - 1){
            songId++;
            mediaPlayer.stop();
            media = new Media(SongsDAO.setMedia(songId));
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(SongsDAO.getNameSong(songId));
            mediaPlayer.play();
        }else {
            songId = 0;
            mediaPlayer.stop();
            media = new Media(SongsDAO.setMedia(songId));
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(SongsDAO.getNameSong(songId));
            mediaPlayer.play();
        }
    }
    public void previousMedia(ActionEvent actionEvent) {
        if (songId > 0){
            songId--;
            mediaPlayer.stop();
            media = new Media(SongsDAO.setMedia(songId));
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(SongsDAO.getNameSong(songId));
            mediaPlayer.play();
        }else {
            songId = SongsDAO.getSongList().size() - 1;
            mediaPlayer.stop();
            media = new Media(SongsDAO.setMedia(songId));
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(SongsDAO.getNameSong(songId));
            mediaPlayer.play();
        }
    }
    public void exitApp() {
        System.exit(1);
    }


}
