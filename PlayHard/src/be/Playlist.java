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
    private SimpleStringProperty name;
    private String songs;
    private SimpleDoubleProperty time;
    private final ArrayList<Song> songsInPlaylist;


    public Playlist(String name/*, String songs */, double time, int id){
        this.name = new SimpleStringProperty(name);
       /* this.songs = songs;*/
        this.time = new SimpleDoubleProperty(time);
        this.id = id;
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
        this.name = new SimpleStringProperty(name);
    }
    public void setSongs(String songs){
        this.songs = songs;
    }
    public void setTime(double time) {this.time = new SimpleDoubleProperty(time);}
    public String getName(){
        return name.get();
    }
    public StringProperty getSongs(){
        songs = songsInPlaylist.size() + "";
        StringProperty propertySongs = new SimpleStringProperty(songs);
        return propertySongs;
    }
    public double getTime() {return time.get();}
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

    @Override
    public String toString() {
        return "Playlist{" +
                ", id=" + id +
                ", time=" + time +
                ", name=" + name +   '}';
    }
}
