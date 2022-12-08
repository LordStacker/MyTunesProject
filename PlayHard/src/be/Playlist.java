package be;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import bll.util.IdGen;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Playlist implements Serializable {
    private int id = 0;
    private String name;
    private String songs;
    private Double time;
    private final ArrayList<Song> songsInPlaylist;


    public Playlist(String name, String songs , double time){
        this.name = name;
        this.songs = songs;
        this.time = time;
        songsInPlaylist = new ArrayList<>();
        /*
        try{
            id = IdGen.createSongId();
        }catch (FileNotFoundException exception){
            System.out.println("File not found!");
        }

         */
    }

    public void setName(String name){
        this.name = name;
    }
    public void setSongs(String songs){
        this.songs = songs;
    }
    public void setTime(Double time){
        this.time = time;
    }
    public StringProperty getName(){
        StringProperty propertyName = new SimpleStringProperty(name);
        return propertyName;
    }
    public StringProperty getSongs(){
        songs = songsInPlaylist.size() + "";
        StringProperty propertySongs = new SimpleStringProperty(songs);
        return propertySongs;
    }
    public DoubleProperty getTime(){
        DoubleProperty propertyTime = new SimpleDoubleProperty(time);
        return propertyTime;
    }
    public ArrayList<Song> getSongsInPlaylist() {
        return songsInPlaylist;
    }
    public void addSong(Song songs){
        songsInPlaylist.add(songs);
    }

    public void removeSong(Song song) {
        songsInPlaylist.remove(songs);
    }
    public int getId() {
        return id;
    }
}
