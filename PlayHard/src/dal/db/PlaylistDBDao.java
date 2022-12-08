package dal.db;

import be.Playlist;
import be.Song;
import dal.SongsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDBDao {

    private static DataBaseConnection dataBaseConnection;

    private static SongsDAO songs = new SongsDAO();

    private static ObservableList<Playlist> allPlayList = FXCollections.observableArrayList();

    public PlaylistDBDao(){
        dataBaseConnection = new DataBaseConnection();

    }

    public static ObservableList<Playlist> getAllPlaylists() throws SQLException{
        try(Connection connection = dataBaseConnection.getConnection()) {
            String sql = "SELECT * FROM playlist;";
            Statement statement = connection.createStatement();
            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int playlistid = resultSet.getInt("Playlistid");
                    String name = resultSet.getString("name");
                    double Time = resultSet.getDouble("Time");
                    System.out.println(playlistid + " " + name + " " + Time);
                    Playlist newPlaylists = new Playlist(name,Time, playlistid);
                    allPlayList.addAll(newPlaylists);


                }
            }
        }

        return allPlayList;
    }

    public static void main(String[] args) throws SQLException {
        PlaylistDBDao playlistDBDao = new PlaylistDBDao();

        List<Playlist> allPlaylists = PlaylistDBDao.getAllPlaylists();
        System.out.println(allPlaylists);
    }

}
