package be;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.util.ArrayList;

public class Songs {
    private SimpleStringProperty title;
    private SimpleStringProperty artist;
    private SimpleStringProperty category;
    private SimpleDoubleProperty time;
    private int id;
    /*
    TODO Move logic DAO -> Songs;
    TODO Singular NAMES FOR EVERYTHING;
     */

    private File source;




    public Songs(String artist, String title, double time, int id, String category, File source){
        this.title = new SimpleStringProperty(title);
        this.artist = new SimpleStringProperty(artist);
        this.category = new SimpleStringProperty(category);
        this.time = new SimpleDoubleProperty(time);
        this.id = id;

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
