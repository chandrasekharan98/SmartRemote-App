package info.androidhive.bottomnavigation;

/**
 * Created by welcome on 27-03-2018.
 */

public class Zmovies {
    private String movietitle;
    private String channelname;
    private int thumbnail;

    public Zmovies() {
    }

    public Zmovies(String movietitle, String channelname, int thumbnail) {
        this.movietitle = movietitle;
        this.channelname = channelname;
        this.thumbnail = thumbnail;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
