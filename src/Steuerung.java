package src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Steuerung implements KeyListener {
    public boolean up1Pressed = false;
    public boolean up2Pressed = false;
    public boolean right1Pressed = false;
    public boolean right2Pressed = false;
    public boolean left1Pressed = false;
    public boolean left2Pressed = false;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up1Pressed = true;
            case KeyEvent.VK_D -> right1Pressed = true;
            case KeyEvent.VK_A -> left1Pressed = true;
            case KeyEvent.VK_UP -> up2Pressed = true;
            case KeyEvent.VK_RIGHT -> right2Pressed = true;
            case KeyEvent.VK_LEFT -> left2Pressed = true;
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
        }
    }
}