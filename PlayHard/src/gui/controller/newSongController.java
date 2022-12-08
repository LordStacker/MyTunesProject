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
import javafx.stage.FileChooser.ExtensionFilter;

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

    public void addMoreCatogories(){
        System.out.println("closing");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }


    public void saveButton(ActionEvent actionEvent) {
        System.out.println("saving");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        categoryChoice.setOnAction(this::getCategory);
        System.out.println(titleField.getText() + " " + artistField.getText() + " " + timeField.getText() +" "+ fileField.getText()+ " ");

        //create extension filter

        ExtensionFilter ex1 = new ExtensionFilter("Mp3 Files","*.mp3");
        ExtensionFilter ex2 = new ExtensionFilter("All Files","*.*");

        //File chooser
        Stage stageDir = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(ex1, ex2);
        File selectedFinalDir = fileChooser.showSaveDialog(stageDir);

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
        System.out.println("Open File");
        System.out.println(selectedFile.getPath());
        }
        //File chooser


        selectedSong.renameTo(new File(selectedFinalDirPath));
        stage.close();
    }


    public void chooseButtonAction(ActionEvent actionEvent) {

        ExtensionFilter ex1 = new ExtensionFilter("Mp3 Files","*.mp3");
        ExtensionFilter ex2 = new ExtensionFilter("All Files","*.*");

        //File chooser
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(ex1, ex2);
        selectedSong = fileChooser.showOpenDialog(stage);
        //File chooser

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            System.out.println("Open File");
            System.out.println(selectedFile.getPath());
        }

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
