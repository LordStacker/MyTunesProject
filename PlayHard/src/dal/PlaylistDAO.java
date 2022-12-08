package dal;

import be.Playlist;
import be.Song;
import dal.db.PlaylistDBDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class PlaylistDAO {

    private static ObservableList<Playlist> playlists = FXCollections.observableArrayList();

    private static PlaylistDBDao playlistDBDao = new PlaylistDBDao();




    public ObservableList<Playlist> getAllPlaylists(ObservableList<Playlist> playlists) throws SQLException {
        this.playlists = playlists;
        return playlists;
    }

    public static ObservableList<Playlist> getPlaylist() throws SQLException {
        ObservableList<Playlist> allPlaylist = FXCollections.observableArrayList();
        allPlaylist.addAll(playlistDBDao.getAllPlaylists());
        return allPlaylist;
    }
}
