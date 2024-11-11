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

        solidArea = new Rectangle(6, 6, 12, 12);//not ready yet

        setSizeAndSpeed();
        getPacManImage();
    }


    public void setSizeAndSpeed() {
        screenX = 280;//the starting position
        screenY = 300;//the starting position
        speed = 4;
        direction = "down";
    }

    public void getPacManImage() {
        try {
            String path = "/resources/image/imageEntity/PacManImage/";
            up1 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(path + "pacman_right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void snapToGrid(String direction) {
        switch (direction) {
            case "up", "down":
                screenX = Math.round((float) screenX / gamePanel.tileSize) * gamePanel.tileSize;
                break;
            case "right", "left":
                screenY = Math.round((float) screenY / gamePanel.tileSize) * gamePanel.tileSize;
                break;
        }
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //if collision is false pacMan cant move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        screenY -= speed;
                        break;
                    case "down":
                        screenY += speed;
                        break;
                    case "left":
                        screenX -= speed;
                        break;
                    case "right":
                        screenX += speed;
                        break;
                }
            }
        }
        snapToGrid(direction);//not ready yet
        spriteCounter++;
        if (spriteCounter > 6) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
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
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize - 6, gamePanel.tileSize - 6, null);
    }
}

