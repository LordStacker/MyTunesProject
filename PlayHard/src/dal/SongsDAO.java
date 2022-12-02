package dal;

import be.Songs;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.util.ArrayList;

public class SongsDAO {

    private ArrayList<File> songList = new ArrayList<>();
    private File[] file;

    private Songs song ;

    private javafx.scene.media.Media media;

    private ObservableList<String> songs;

    private static final String SONGS_SOURCE = "PlayHard/data/songs";


    public void getAllSongs(){
        File folder = new File(SONGS_SOURCE);
        this.file = folder.listFiles();
        //Filing the array with the info of the folder
        if(folder != null){
            for (File f: file) {
                System.out.println(f);
                songList.add(f);
                //Getting all the files inside the ArrayList
            }
        }
    }

    public void getTitleSong(){

        String title;
        String[] titles;
        if(songList.size() > 1){
            for(int i = 0; i < songList.size(); i++){
                title = songList.get(i).toString();
                titles = title.split("-", 2);
                titles = titles[1].split(",",2);
                System.out.println(titles[0].trim());
                System.out.println(titles[1].trim());
            }
        }
    }

    public ArrayList getSongList(){
        return songList;
    }

    public String setMedia(int songId){
        return songList.get(songId).toURI().toString();
    }

    public String getNameSong(int songId){
        return songList.get(songId).getName();
    }

}
