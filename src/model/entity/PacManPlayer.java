package model.entity;

import control.KeyHandler;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PacManPlayer extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public PacManPlayer(GamePanel gp, KeyHandler keyH) {
        this.gamePanel = gp;
        this.keyHandler = keyH;
        setSizeAndSpeed();
        getPacManImage();
    }

    public void setSizeAndSpeed() {
        screenX = 100;
        screenY = 100;
        speed = 4;
        direction = "down";
    }

    public void getPacManImage() {
        try {
            String path = "/resources/image/imageEntity/PacManImage/";
            up1 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(path +"pacman_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream(path +"pacman_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(path +"pacman_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream(path +"pacman_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(path +"pacman_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream(path +"pacman_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(path +"pacman_right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
                screenY -= speed;
            }
            if (keyHandler.downPressed) {
                direction = "down";
                screenY += speed;
            }
            if (keyHandler.leftPressed) {
                direction = "left";
                screenX -= speed;
            }
            if (keyHandler.rightPressed) {
                direction = "right";
                screenX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
