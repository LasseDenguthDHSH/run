package src.player;

import src.panel.GamePanel;

import java.awt.event.*;

public class Steuerung implements KeyListener {
    private boolean up1Pressed = false;
    private boolean up2Pressed = false;
    private boolean right1Pressed = false;
    private boolean right2Pressed = false;
    private boolean left1Pressed = false;
    private boolean left2Pressed = false;
    private boolean escapePressed = false;

    public Steuerung() {
    }

    public boolean isUp1Pressed() {
        return up1Pressed;
    }

    public boolean isUp2Pressed() {
        return up2Pressed;
    }

    public boolean isRight1Pressed() {
        return right1Pressed;
    }

    public boolean isRight2Pressed() {
        return right2Pressed;
    }

    public boolean isLeft1Pressed() {
        return left1Pressed;
    }

    public boolean isLeft2Pressed() {
        return left2Pressed;
    }
    public boolean isEscapePressed() {
        return escapePressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up1Pressed = true;
            case KeyEvent.VK_D -> right1Pressed = true;
            case KeyEvent.VK_A -> left1Pressed = true;
            case KeyEvent.VK_UP -> up2Pressed = true;
            case KeyEvent.VK_RIGHT -> right2Pressed = true;
            case KeyEvent.VK_LEFT -> left2Pressed = true;
            case KeyEvent.VK_ESCAPE -> escapePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up1Pressed = false;
            case KeyEvent.VK_D -> right1Pressed = false;
            case KeyEvent.VK_A -> left1Pressed = false;
            case KeyEvent.VK_UP -> up2Pressed = false;
            case KeyEvent.VK_RIGHT -> right2Pressed = false;
            case KeyEvent.VK_LEFT -> left2Pressed = false;
            case KeyEvent.VK_ESCAPE -> escapePressed = false;
        }
    }
}