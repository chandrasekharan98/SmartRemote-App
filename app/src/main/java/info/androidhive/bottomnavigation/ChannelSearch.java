package info.androidhive.bottomnavigation;

/**
 * Created by welcome on 29-03-2018.
 */

public class ChannelSearch {
    String channelname,channelnumber;

    public ChannelSearch() {
    }

    public ChannelSearch(String channelname, String channelnumber) {
        this.channelname = channelname;
        this.channelnumber = channelnumber;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getChannelnumber() {
        return channelnumber;
    }

    public void setChannelnumber(String channelnumber) {
        this.channelnumber = channelnumber;
    }
}
