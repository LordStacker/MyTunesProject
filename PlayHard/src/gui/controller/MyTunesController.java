package gui.controller;

import be.Playlist;
import be.Song;
import bll.util.Filter;
import dal.PlaylistDAO;
import dal.SongsDAO;
import dal.db.PlaylistDBDao;
import dal.db.SongDBDao;
import gui.MyTunes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MyTunesController implements Initializable {
    //table columns
    public TableView<Song> songsTable;
    public TableColumn<Song, String> titleColumn;
    public TableColumn<Song, String> artistColumn;
    public TableColumn<Song, String> categoryColumn;
    public TableColumn<Song, Integer> timeColumn;
    public TextField searchBar;

    public TableView<Playlist> playlistTable;
    public TableColumn<Playlist, String> nameColumn;
    public TableColumn<Playlist, Integer> timePlayListColumn;


    private ArrayList<Stage> listOfStages = new ArrayList<>();

    private SongsDAO SongsDAO = new SongsDAO();
    
    private PlaylistDAO playlistDAO = new PlaylistDAO();

    private Filter filter = new Filter();

    public Slider volumeSlider;

    private javafx.scene.media.Media media;

    private  boolean running;

    private int songId;

    @FXML
    public Label songLabel;

    public MediaPlayer mediaPlayer;

    @FXML
    private Button playBtn;
    private ObservableList<Song> songs = FXCollections.observableArrayList();

    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();

    private PlaylistDBDao playlistDBDao = new PlaylistDBDao();





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songs.addAll(SongsDAO.getAllSongs(SongsDAO.setAllSongs()));
        //table view
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
        songsTable.setItems(songs);
        try {
            playlists.addAll(playlistDAO.getPlaylist());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            SongsDAO.clearSongs();
            SongsDAO.addSongDB(songs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        playlistTable.setItems(playlists);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        timePlayListColumn.setCellValueFactory(new PropertyValueFactory<>("time"));



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
        //search bar logic
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                songs.clear();
                songs.addAll(filter.searchSong(newValue));

            }
        });


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

        Stage stage = new Stage();
        stage.setTitle("Edit a song");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public void openAddPlaylist() throws IOException {
        FXMLLoader loader = new FXMLLoader(MyTunes.class.getResource("view/newPlaylist.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stageAddPlayList = new Stage();

        listOfStages.add(stageAddPlayList);
        stageAddPlayList.setTitle("Add a playlist");
        stageAddPlayList.setScene(scene);
        stageAddPlayList.show();
        stageAddPlayList.setResizable(false);
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


    public void playSelectedSong(MouseEvent mouseEvent) {
        mediaPlayer.stop();
        Song selectedSong = songsTable.getSelectionModel().getSelectedItem();
       // Song songToPlay = new Song(Paths.get(selectedSong.getPath().toUri()).toUri().toString());
        if (selectedSong != null){
            media = new Media(selectedSong.getSource());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }
}
