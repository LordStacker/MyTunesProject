package dal;


import be.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class SongsDAO {

    private ArrayList<File> songList = new ArrayList<>();
    private File[] file;

    private  ObservableList<Song> songs = FXCollections.observableArrayList();

    private ArrayList<String> titleSongs = new ArrayList<>();

    private static final String SONGS_SOURCE = "PlayHard/data/songs";


    public ObservableList<Song> setAllSongs(){
        File folder = new File(SONGS_SOURCE);
        this.file = folder.listFiles();
        //Filing the array with the info of the folder
        if(folder != null){
            for (File f: file) {
                System.out.println(f);
                songList.add(f);
            }
            for(int i=0 ; i<songList.size();i++){
                String title;
                String[] titles = new String[0];
                title = songList.get(i).toString();
                titles = title.split("-", 2);
                Song songListed = new Song(titles[1],titles[1], 11, "atanas");
                songs.add(songListed);
            }
        }
        return songs;
    }
    public ObservableList<Song> getAllSongs(ObservableList<Song> songs){
        this.songs = songs;
        return songs;
    }

    private int getNextId(){
        ObservableList<Song>  songsId = setAllSongs();
        int lastId= 0;
        for(Song s : songsId){
            if(lastId<s.getId()){
                lastId = s.getId();
            }
        }
        return lastId+1;
    }

    public ObservableList<Song> getSongs() {
        ObservableList<Song> allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songs);
        return allSongs;
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
