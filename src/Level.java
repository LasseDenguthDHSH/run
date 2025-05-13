package src;

public abstract class Level {
    protected int groundY;
    protected String backgroundImage;
    protected String player1ImagePath;
    protected String player2ImagePath;

    public abstract void setupLevel();

    public int getGroundY() {
        return groundY;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getPlayer1ImagePath() {
        return player1ImagePath;
    }

    public String getPlayer2ImagePath() {
        return player2ImagePath;
    }

    public int getPlayer1StartX() {
        return 50;
    }

    public int getPlayer1StartY() {
        return groundY - 32;
    }

    public int getPlayer2StartX() {
        return 50;
    }

    public int getPlayer2StartY() {
        return groundY - 32;
    }
}