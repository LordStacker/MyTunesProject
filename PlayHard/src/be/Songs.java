package be;

import java.io.File;
import java.util.ArrayList;

public class Songs {

    private int id;
    private String artist;
    private String category;
    private double time;
    /*
    TODO Move logic DAO -> Songs;
    TODO Singular NAMES FOR EVERYTHING;
     */

    private File source;

    private String title;



    public Songs(String artist, String title, double time, int id, String category, File source){
        this.artist = artist;
        this.title = title;
        this.time = time;
        this.id = id;
        this.category = category;
        this.source = source;
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

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public double getTime() {
        return time;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

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
