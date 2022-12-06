package bll.util;

public class IdGen {

    private static int songId = 0;


    private static int playListId = 0;



    public static int createSongId() {
        songId++;
        return songId;
    }


    /*
    TODO
    public static int createPlaylistId()
    */
}
