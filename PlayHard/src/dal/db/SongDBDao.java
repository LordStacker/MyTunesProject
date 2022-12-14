package dal.db;

import be.Song;
import dal.SongsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDBDao {

    private static DataBaseConnection dataBaseConnection;

    public SongDBDao(){
        dataBaseConnection = new DataBaseConnection();

    }
    private static ObservableList<Song> songForPlayList = FXCollections.observableArrayList();


    public List<Song> getAllSongs() throws SQLException{
        ArrayList<Song> allSongs = new ArrayList<>();
    try(Connection connection = dataBaseConnection.getConnection()) {
        String sql = "SELECT * FROM Songs;";

        Statement statement = connection.createStatement();

        if(statement.execute(sql)){
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                int  SongId = resultSet.getInt("SongID");
                String  Tittle = resultSet.getString("Title");
                String Source = resultSet.getString("Source");
                String Artist = resultSet.getString("Artist");
                String Category = resultSet.getString("Category");
                double Time = resultSet.getDouble("Time");
            }

        }
    }
    return allSongs;
    }

    public void postSongs(int id, String title, String artist, String category, double time, String source) throws SQLException{
        try(Connection connection = dataBaseConnection.getConnection()) {
            String sql = "SET ANSI_WARNINGS OFF INSERT INTO Songs(SongID,Title,Source,Artist,Category,[time]) VALUES ("+ id +",'"+ title+"','"+source+"','"+ artist+"','"+ category +"',"+ time +") SET ANSI_WARNINGS ON;";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Inserted correctly");
                }
        }

    }

    public static void clearSongsDB() throws  SQLException{
        try(Connection connection = dataBaseConnection.getConnection()){
            String sql = "Delete from songs;";
            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Deleted");
            }
        }
    }

    public static ObservableList<Song> getSongsForPlaylist(int id) throws SQLException {
        try (Connection connection = dataBaseConnection.getConnection()) {
            String sql = "SELECT * FROM Songs where SongID=" + id + ";";

            Statement statement = connection.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int SongId = resultSet.getInt("SongID");
                    String Tittle = resultSet.getString("Title");
                    String Source = resultSet.getString("Source");
                    String Artist = resultSet.getString("Artist");
                    String Category = resultSet.getString("Category");
                    double Time = resultSet.getDouble("Time");
                    Song songs = new Song(Artist, Tittle, Time, Category, Source);
                    songForPlayList.add(songs);
                }

            }

        }
        return songForPlayList;
    }

    public static void main(String[] args) throws SQLException {
        SongDBDao songDBDao = new SongDBDao();
        List<Song> allSongs = songDBDao.getAllSongs();
    }
}
