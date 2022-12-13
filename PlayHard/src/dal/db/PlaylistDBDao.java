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

    public static void postNewSongsToPlayList(int id, String playListName, int songId) throws  SQLException{
        try(Connection connection = dataBaseConnection.getConnection()){
            String sql = "INSERT INTO playlistinfo (Playlistid, name, songsinplaylist) values ("+ id +"'"+playListName+"'"+ songId+");";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted");
            }
        }
    }
    public static void getAllSongsFromPlayList(String name) throws SQLException{
        try(Connection connection = dataBaseConnection.getConnection()){
            String sql = "SELECT * FROM playlistinfo where name="+name+";";
        }
    }

    public static ObservableList<Song> getSongsFromPlaylist(String input) throws  SQLException{
        int i = 0;
        try(Connection connection = dataBaseConnection.getConnection()){
            String sql = "SELECT * from playlistinfo where name='"+input+"';";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    int playlistid = resultSet.getInt("Playlistid");
                    String name = resultSet.getString("name");
                    int songsinplaylist = resultSet.getInt("songsinplaylist");
                    SongDBDao.getSongsForPlaylist(songsinplaylist);
                    songForPlayList.add(SongDBDao.getSongsForPlaylist(songsinplaylist).get(i));
                    i++;
                   /* for(int i = 0; i<SongDBDao.getSongsForPlaylist(songsinplaylist).size(); i++){
                        songForPlayList.add(SongDBDao.getSongsForPlaylist(songsinplaylist).get(i));
                    }*/
                }
            }
            return songForPlayList;
        }
    }

    public static void main(String[] args) throws SQLException {
        PlaylistDBDao playlistDBDao = new PlaylistDBDao();
        getSongsFromPlaylist("PlayListTest");

        List<Playlist> allPlaylists = PlaylistDBDao.getAllPlaylists();
    }

}
