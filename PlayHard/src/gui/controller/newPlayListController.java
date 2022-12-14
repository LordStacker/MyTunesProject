package gui.controller;

import be.Playlist;
import dal.PlaylistDAO;
import dal.db.PlaylistDBDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class newPlayListController {

    @FXML
    private Button closeButton, saveButton;

    @FXML
    private TextField playListNameField;

    private String playListName;

    public void cancelButtonAction(){
        System.out.println("closing");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void PlayListName(){
        this.playListName = playListNameField.getText();
    }

    public void savePlayList() throws SQLException {
        PlayListName();
        Playlist playlist = new Playlist(playListName, 55.34);
        PlaylistDBDao.postNewPlaylists(playlist.getId(),playlist.getName(), playlist.getTime());
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
