package bll.util;

import be.Song;
import dal.SongsDAO;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.List;

public class Filter  {

private SongsDAO songsDAO = new SongsDAO();
private final ObservableList songs = songsDAO.setAllSongs();

    public List<Song> searchSong(String query){

        ObservableList<Song> songs = songsDAO.getAllSongs(this.songs);
        FilteredList<Song> filteredList = new FilteredList<>(songs, b -> true);
        filteredList.setPredicate(song ->{
            if (song.getTitle().toLowerCase().contains(query.toLowerCase())
                    || song.getArtist().toLowerCase().contains(query.toLowerCase())
                    || song.getCategory().toLowerCase().contains(query.toLowerCase()))
                return true;
            else
                return false;
        });
        return filteredList;
    }

}
