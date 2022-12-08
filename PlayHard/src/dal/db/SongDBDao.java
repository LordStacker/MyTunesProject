package dal.db;

import be.Song;
import dal.SongsDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDBDao {

    private DataBaseConnection dataBaseConnection;

    public SongDBDao(){
        dataBaseConnection = new DataBaseConnection();

    }


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
                System.out.println(SongId + " " + Tittle + " " + " " + Source + " " + Artist + " " + Category + " " + Time + " ");


            }

        }
    }
    return allSongs;
    }

    public static void main(String[] args) throws SQLException {
        SongDBDao songDBDao = new SongDBDao();

        List<Song> allSongs = songDBDao.getAllSongs();

        System.out.println(allSongs);
    }
}
