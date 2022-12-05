package dal;


import be.Songs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.File;
import java.io.Serial;
import java.util.ArrayList;

public class SongsDAO {

    private ArrayList<File> songList = new ArrayList<>();
    private File[] file;

    private Songs song ;

    private javafx.scene.media.Media media;

    private ObservableList<Songs> songs = FXCollections.observableArrayList();

    private ArrayList<String> titleSongs = new ArrayList<>();

    private static final String SONGS_SOURCE = "PlayHard/data/songs";


    public ObservableList<Songs> getAllSongs(){
        File folder = new File(SONGS_SOURCE);
        this.file = folder.listFiles();
        //Filing the array with the info of the folder
        if(folder != null){
            for (File f: file) {
                System.out.println(f);
                songList.add(f);
                Songs songListed = new Songs("random","random2", 11, file.length, "atanas", f);
                songs.add(songListed);
               /* System.out.println(songs);*/
                for(int i = 0; i< songs.size(); i++){
                    System.out.println(songs.get(i).toString());
                }
                //Getting all the files inside the ArrayList
            }
        }
        return songs;
    }

    public ObservableList<Songs> getSongs() {
        return songs;
    }

    public ArrayList<String> getTitleSong(){

        String title;
        String[] titles = new String[0];
        if(songList.size() > 1){
            for(int i = 0; i < songList.size(); i++){
                title = songList.get(i).toString();
                titles = title.split("-", 2);
                titleSongs.add(titles[1]);

            }
        }
        return titleSongs;

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
