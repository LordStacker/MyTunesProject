package gui.controller;

import be.Song;
import dal.SongsDAO;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
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
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.stage.FileChooser.ExtensionFilter;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

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


    public void cancelButtonAction() {
        System.out.println("closing");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }

    public void addMoreCatogories() {
        System.out.println("closing");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }


    public void saveButton(ActionEvent actionEvent) {
        System.out.println("saving");
        Stage stage = (Stage) scenePane.getScene().getWindow();
        categoryChoice.setOnAction(this::getCategory);

        //create extension filter
        ExtensionFilter ex1 = new ExtensionFilter("Mp3 Files", "*.mp3");
        ExtensionFilter ex2 = new ExtensionFilter("All Files", "*.*");


        //File chooser
        Stage stageDir = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(ex1, ex2);
        File selectedFinalDir = fileChooser.showSaveDialog(stageDir);
        selectedFinalDirPath= String.valueOf(selectedFinalDir);
        selectedSong.renameTo(new File(selectedFinalDirPath));
        SongsDAO songsDAO = new SongsDAO();
        songsDAO.setAllSongs();
        stage.close();
    }


    public void chooseButtonAction(ActionEvent actionEvent) {


        ExtensionFilter ex1 = new ExtensionFilter("Mp3 Files", "*.mp3");
        ExtensionFilter ex2 = new ExtensionFilter("All Files", "*.*");

        //File chooser
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(ex1, ex2);
        selectedSong = fileChooser.showOpenDialog(stage);
        //File chooser


        filePath =selectedSong.toString();
        AudioFile af = null;
        try {
            af = AudioFileIO.read(selectedSong);
        } catch (CannotReadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TagException e) {
            throw new RuntimeException(e);
        } catch (ReadOnlyFileException e) {
            throw new RuntimeException(e);
        } catch (InvalidAudioFrameException e) {
            throw new RuntimeException(e);
        }
        AudioHeader ah = af.getAudioHeader();
        int songLength=ah.getTrackLength()/60;
        timeField.setText(String.valueOf(songLength));
        fileField.setText(String.valueOf(selectedSong));
    }

    public void getCategory(ActionEvent event){
        String selectedCategory = categoryChoice.getValue();
        System.out.println("category set: "+selectedCategory);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryChoice.getItems().addAll(categories);
        categoryChoice.setOnAction(this::getCategory);
    }
}
