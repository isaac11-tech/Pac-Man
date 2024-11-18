package model.entity;

import control.KeyHandler;
import model.objects.Coin;
import model.objects.SuperCoin;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PacManPlayer extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    Coin coin;
    SuperCoin superCoin;
    int cunt;
    int speed = 3;
    int cuntPixel = 0;
    boolean isMoving = false;

    public PacManPlayer(GamePanel gp, KeyHandler keyH, Coin coin, SuperCoin superCoin) {
        this.gamePanel = gp;
        this.keyHandler = keyH;
        this.coin = coin;
        this.superCoin = superCoin;
        cunt = gamePanel.tileSize;
        setSizeAndSpeed();
        getPacManImage();
    }


    public void setSizeAndSpeed() {
        positionX = 17;
        positionY = 14;
        direction = "down";
    }

    public void getPacManImage() {
        try {
            String path = "/resources/image/imageEntity/PacManImage/";
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "pacman_right2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (!isMoving) {

            if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

                if (keyHandler.upPressed) {
                    direction = "up";
                } else if (keyHandler.downPressed) {
                    direction = "down";
                } else if (keyHandler.leftPressed) {
                    direction = "left";
                } else if (keyHandler.rightPressed) {
                    direction = "right";
                }

                collisionOn = false;
                gamePanel.collisionChecker.checkTile(this);

                if (!collisionOn || isTransition()) {
                    isMoving = true;
                    cuntPixel = 0;
                }
            }
        } else {

            cuntPixel += speed;

            if (cuntPixel >= cunt) {
                switch (direction) {
                    case "up":
                        positionY--;
                        break;
                    case "down":
                        positionY++;
                        break;
                    case "left":
                        if (positionX == 5 && positionY == 14) {
                            positionX = 29;
                        } else {
                            positionX--;
                        }
                        break;
                    case "right":
                        if (positionX == 29 && positionY == 14) {
                            positionX = 5;
                        } else {
                            positionX++;
                        }
                        break;
                }
                System.out.println(positionX+" "+positionY);
                coin.mapCoin[positionX][positionY] = false;
                superCoin.mapCoin[positionX][positionY] = false;
                cuntPixel = 0;
                isMoving = false;
            }
        }

        spriteCounter++;
        if (spriteCounter > 8) {
            if (isMoving) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        screenX = positionX * gamePanel.tileSize;
        screenY = positionY * gamePanel.tileSize;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                screenY -= cuntPixel;
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                screenY += cuntPixel;
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                screenX -= cuntPixel;
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                screenX += cuntPixel;
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize - 6, gamePanel.tileSize - 6, null);
    }
    public boolean isTransition() {
        return (positionX == 5 && positionY == 14) || (positionX == 29 && positionY == 14);
    }
}
