package be;

import bll.util.IdGen;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;

public class Song {
    private SimpleStringProperty title;
    private SimpleStringProperty artist;
    private SimpleStringProperty category;
    private SimpleDoubleProperty time;
    private int id = 0;
    private String fileName;


    private File source;




    public Song(String artist, String title, double time, String category){
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.category = new SimpleStringProperty(category);
        this.time = new SimpleDoubleProperty(time);
        this.id = IdGen.createSongId();

    }



    public int getId() {
        return id;
    }

    public File getSource(){
        return this.source;
    }

    public File setSource(File source){
        this.source = source;
        return this.source;
    }
    public void setFilePath(String path){
        this.fileName = path;
    }
    public StringProperty getFileName(){
        StringProperty propertyFileName = new SimpleStringProperty(fileName);
        return propertyFileName;
    }

    public String getTitle (){return  title.get();}
    public double getTime() {return time.get();}
    public String getArtist() {
        return artist.get();
    }
    public String getCategory() {
        return category.get();
    }
    public void setTitle(String title) {this.title =new SimpleStringProperty(title);}
    public void setArtist(String artist) {this.artist =new SimpleStringProperty(artist);}
    public void setCategory(String category) {this.category = new SimpleStringProperty(category);}
    public void setTime(double time) {this.time = new SimpleDoubleProperty(time);}

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ",title='" + title + '\'' +
                ", time=" + time +
                ", Artist=" + artist +
                ", Category=" + category +

                '}';
    }
}
