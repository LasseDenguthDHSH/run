package src;
public abstract class Level {
    protected int groundY;
    protected String backgroundImage;

    public abstract void setupLevel();

    public int getGroundY() {
        return groundY;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }
}
