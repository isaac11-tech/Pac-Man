package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override//function that get pressed from user and update the boolean
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            upPressed = true;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            upPressed = false;
            downPressed = true;
            leftPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            upPressed = false;
            downPressed = false;
            leftPressed = true;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = true;
        }
    }

    @Override//function that after pressed update the boolean to false
    public void keyReleased(KeyEvent e) {

    }
}
