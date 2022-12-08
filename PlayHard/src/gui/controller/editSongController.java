package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class editSongController {
    @FXML
    private BorderPane scenePane;


    public void saveEditButton() {
        System.out.println("saving");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }


    public void cancelEditButtonAction(){
        System.out.println("closing");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
}
