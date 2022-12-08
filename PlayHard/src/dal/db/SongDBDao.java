package dal.db;

import be.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDBDao {

    private DataBaseConnection dataBaseConnection;

    private SongDBDao(){
        dataBaseConnection = new DataBaseConnection();

    }


    public List<Song> getAllSongs() throws SQLException{
        ArrayList<Song> allSongs = new ArrayList<>();
    try(Connection connection = dataBaseConnection.getConnection()) {
        String sql = "SELECT * FROM Song;";

        Statement statement = connection.createStatement();

        if(statement.execute(sql)){
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()){
                String  test = resultSet.getString("Song");
                System.out.println(test);
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
