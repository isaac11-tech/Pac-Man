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

import static model.entity.Ghost.isFrightenedMode;
import static model.objects.SuperCoin.mapSuperCoin;
import static view.GamePanel.tileSize;

public class PacManPlayer extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    Coin coin;
    SuperCoin superCoin;
    int cunt;
    int cuntPixel = 0;
    boolean isMoving = false;
    public int cuntScore = 0;

    public PacManPlayer(GamePanel gp, KeyHandler keyH, Coin coin, SuperCoin superCoin) {
        this.gamePanel = gp;
        this.keyHandler = keyH;
        this.coin = coin;
        this.superCoin = superCoin;
        cunt = tileSize;
        speed = 3;
        point = new Point();
        setSizeAndSpeed();
        getPacManImage();
    }

    public void setSizeAndSpeed() {
        point.x = 12;
        point.y = 25;
        direction = "right";
    }
    public void returnToBasePoz(){
        point.x = 12;
        point.y = 25;
    }
    public Point getPacManPoz(){
        return point;
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
                        point.y--;
                        break;
                    case "down":
                        point.y++;
                        break;
                    case "left":
                        if (point.x == 0 && point.y == 14) {
                            point.x = 24;
                        } else {
                            point.x--;
                        }
                        break;
                    case "right":
                        if (point.x == 24 && point.y == 14) {
                            point.x = 0;
                        } else {
                            point.x++;
                        }
                        break;
                }
                System.out.println(point.x + " " + point.y);
                if (coin.mapCoin[point.x][point.y]) {
                    cuntScore += coin.coinValue;
                    coin.mapCoin[point.x][point.y] = false;
                }
                if (mapSuperCoin[point.x][point.y]) {
                    cuntScore += superCoin.coinValue;
                    mapSuperCoin[point.x][point.y] = false;
                    Ghost.startFrightenedMode();
                }
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
        screenX = point.x * tileSize;
        screenY = point.y * tileSize;
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
        g2.drawImage(image, screenX, screenY, tileSize - 6, tileSize - 6, null);
    }

    public boolean isTransition() {//check if we are in the transition
        return (point.x == 0 && point.y == 14) || (point.x == 24 && point.y == 14);
    }

    public int getHighScore() {
        int highScore = 0;
        if (cuntScore > highScore) {
            highScore = cuntScore;
        }
        return highScore;
    }
}
