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

    private static ObservableList<Song> songForPlayList = FXCollections.observableArrayList();

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
                    Playlist newPlaylists = new Playlist(name,Time);
                    allPlayList.addAll(newPlaylists);


                }
            }
        }

        return allPlayList;
    }
    public static void postNewPlaylists(int id,String playlist, double time) throws SQLException{
        try(Connection connection = dataBaseConnection.getConnection()) {
            String sql = "INSERT INTO playlist(playlistid,name,[time]) VALUES ("+ id +",'"+ playlist+"',"+time+");";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
            }
        }
    }

    public static void addNewSongsToPlayList(int idPlayList, String playListName, int songId) throws  SQLException{
        try(Connection connection = dataBaseConnection.getConnection()){
            String sql = "SET ANSI_WARNINGS OFF INSERT INTO playlistinfo (Playlistid, name, songId) values ("+ idPlayList +",'"+playListName+"',"+ songId+") SET ANSI_WARNINGS ON;";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted");
            }
        }
    }
    public static ObservableList<Song> getSongsForPlaylist(String input) throws  SQLException{
        int i = 0;
        try(Connection connection = dataBaseConnection.getConnection()){
            String sql = "SELECT * from playlistinfo where name='"+input+"';";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    int playlistid = resultSet.getInt("Playlistid");
                    String name = resultSet.getString("name");
                    int songsinplaylist = resultSet.getInt("songId");
                    SongDBDao.getSongsForPlaylist(songsinplaylist);
                    songForPlayList.add(SongDBDao.getSongsForPlaylist(songsinplaylist).get(i));
                    i++;
                }
            }
            return songForPlayList;
        }
    }

    public static void deletePlayList(String playListName) throws  SQLException{
        try(Connection connection = dataBaseConnection.getConnection()){
            String sql = "Delete playlist where name='"+playListName+"';";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Deleted"+ playListName);
            }

        }

    }

    public static void main(String[] args) throws SQLException {
        PlaylistDBDao playlistDBDao = new PlaylistDBDao();
        /*getSongsForPlaylist("PlayListTest");
        deletePlayList("martin playlist");*/

        List<Playlist> allPlaylists = PlaylistDBDao.getAllPlaylists();
    }

}
