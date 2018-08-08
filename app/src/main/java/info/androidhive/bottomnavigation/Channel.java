package info.androidhive.bottomnavigation;

/**
 * Created by welcome on 27-03-2018.
 */

public class Channel {
    private String name;
    private int channelno;
    private int thumbnail;

    public Channel() {
    }

    public Channel(String name, int channelno, int thumbnail) {
        this.name = name;
        this.channelno = channelno;
        this.thumbnail = thumbnail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChannelno(int channelno) {
        this.channelno = channelno;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public int getChannelno() {
        return channelno;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
