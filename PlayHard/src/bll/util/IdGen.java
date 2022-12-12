package bll.util;

public class IdGen {

    private static int songId = 0;


    private static int playListId = 0;



    public static int createSongId() {
        songId++;
        System.out.println(songId);
        return songId;
    }

    public static int createPlayListId() {
        playListId++;
        System.out.println(playListId);
        return playListId;
    }

}
