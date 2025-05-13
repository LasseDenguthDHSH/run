package src;

public class Player {
    int width;
    int height;
    int x;
    int y;
    double speed;
    double gravity = 0.7;
    double jumpStrength = 12;
    double velocityY = 0;
    boolean isJumping = false;

    public Player(int width, int height, int x, int y, double speed) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

}
