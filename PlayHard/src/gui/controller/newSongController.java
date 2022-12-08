package gui.controller;

import be.Song;
import dal.SongsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;


public class newSongController implements Initializable {
    @FXML
    private BorderPane scenePane;
    @FXML
    private TextField titleField, artistField, timeField, fileField;

    //private Button chooseButton;

    @FXML
    private ChoiceBox<String> categoryChoice;

    private String filePath;

    private File selectedSong;
    private String selectedFinalDirPath;

    private String[] categories = {"electro", "pop", "rap", "rock"};


    public void cancelButtonAction(){
        System.out.println("closing");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }


    public void saveButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("saving");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        categoryChoice.setOnAction(this::getCategory);
        System.out.println(titleField.getText() + " " + artistField.getText() + " " + timeField.getText() +" "+ fileField.getText()+ " ");

        //File chooser
        Stage stageDir = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedFinalDir = fileChooser.showSaveDialog(stageDir);
        //File chooser


        selectedSong.renameTo(new File(selectedFinalDirPath));
        stage.close();
    }


    public void chooseButtonAction(ActionEvent actionEvent) {
        //File chooser
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        selectedSong = fileChooser.showOpenDialog(stage);
        //File chooser

        System.out.println(selectedSong);
        filePath = selectedSong.toString();
        System.out.println(" here is the filepath" + filePath);
        //selectedSong.renameTo(new File(""));
        fileField.setText(String.valueOf(selectedSong));
    }

    public void getCategory(ActionEvent event){
        String selectedCategory = categoryChoice.getValue();
        System.out.println("category: "+selectedCategory);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryChoice.getItems().addAll(categories);
        //categoryChoice.setOnAction(this::getCategory);
    }
}
