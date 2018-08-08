package info.androidhive.bottomnavigation;

/**
 * Created by welcome on 27-03-2018.
 */

public class Game {
    private String name;
    private int imageSource;

    public Game (int imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
}