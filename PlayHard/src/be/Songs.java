package be;

import java.io.File;
import java.util.ArrayList;

public class Songs {

    private int id;
    private String artist;
    private String category;
    private double time;

    private String title;



    public Songs(String artist, String title){
        this.artist = artist;
        this.title = title;
    }



    public int getId() {
        return id;
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
}
