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

    public void addPlayListDB(ObservableList<Playlist> playlists) throws SQLException {
        for(int i=0; i< playlists.size(); i++){
            PlaylistDBDao.postNewPlaylists(playlists.get(i).getId(), playlists.get(i).getName(), playlists.get(i).getTime());
        }
    }

    public static ObservableList<Playlist> getPlaylist() throws SQLException {
        ObservableList<Playlist> allPlaylist = FXCollections.observableArrayList();
        allPlaylist.addAll(playlistDBDao.getAllPlaylists());
        return allPlaylist;
    }
}
