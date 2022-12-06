package gui.controller;

import be.Songs;
import dal.SongsDAO;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class newSongController {
    @FXML
    private BorderPane scenePane;


    public void saveButton() {
        System.out.println("saving");
        //SongsDAO songsDAO = new SongsDAO();
        //songsDAO.getAllSongs();
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }


    public void cancelButtonAction(){
        System.out.println("closing");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}
