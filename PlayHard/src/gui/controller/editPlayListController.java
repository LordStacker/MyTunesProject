package gui.controller;

import be.Playlist;
import dal.db.PlaylistDBDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class editPlayListController {

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
        /*PlaylistDBDao.postNewPlaylists(playlist.getId(),playlist.getName(), playlist.getTime());*/
        System.out.println(playListName);
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

}
