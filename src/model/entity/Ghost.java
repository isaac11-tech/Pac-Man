package model.entity;

import control.CollisionChecker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static view.GamePanel.*;

abstract public class Ghost extends Entity {
    PacManPlayer pacManPlayer;
    CollisionChecker collisionChecker;
    protected Point target;
    Point sortWay;
    Point scatterPosition;
    //for frightened mode
    BufferedImage frightenedImage;
    static boolean isFrightenedMode = false;
    //fot eaten mode
    boolean isEatenMode = false;
    BufferedImage up;
    BufferedImage down;
    BufferedImage left;
    BufferedImage right;
    int cuntPixel = 0;
    boolean isStart = true;
    String direction = "up";
    int x;
    int y;

    // Add timer variables
    private long lastScatterTime = 0;
    private long lastChaseTime = 0;
    private long lastFrightenedTime = 0;
    private static final long SCATTER_INTERVAL = 7000; // 7 seconds in milliseconds
    private static final long CHASE_INTERVAL = 20000; // 20 seconds in milliseconds
    private static final long FRIGHTENED_INTERVAL = 6000; // 6 seconds in milliseconds
    private boolean isScatterMode = true;

    public Ghost(CollisionChecker collisionChecker, PacManPlayer pacManPlayer) {
        this.pacManPlayer = pacManPlayer;
        this.collisionChecker = collisionChecker;
        this.point = new Point(0, 0);
        this.target = new Point(0, 0);
        this.sortWay = new Point(0, 0);
        getFrightenedImage();
        getEatenImage();
    }

    abstract void setBasePosition();

    abstract Point getBasePosition();

    abstract void setScatterPosition();

    abstract Point getScatterPosition();

    abstract public void scatterMode();

    abstract void chaseMode();//pass

    private void frightenedMode() {
        int randomX = (int) (Math.random() * (screenWidth / tileSize - 1));// not fixed !!!!!!!!!
        int randomY = (int) (Math.random() * (screenHigh / tileSize - 1));
        this.target = new Point(randomX, randomY);
        this.speed = 1;
    }

    public void eatenMode() {
        isEatenMode = true;
        this.target = new Point(getBasePosition());
        speed = 4;
    }

    public void update() {//not finish!!!!!!!
        //  Check and update modes based on timers
        long currentTime = System.currentTimeMillis();
        if (collisionChecker.collisionPacManWithGhost(pacManPlayer,this)){
            if (isFrightenedMode){
                eatenMode();
            }else {
               pacManPlayer.returnToBasePoz();
            }
        }
//        if (isFrightenedMode) {
//            if (currentTime - lastFrightenedTime >= FRIGHTENED_INTERVAL) {
//                isFrightenedMode = false;
//                speed = 2; // Reset speed to normal
//            } else {
//                frightenedMode();
//            }
//            lastFrightenedTime = currentTime;
//            //isFrightenedMode = false;
//        }
//        if (isScatterMode && currentTime - lastScatterTime >= SCATTER_INTERVAL) {
//            chaseMode();
//            lastChaseTime = currentTime;
//            isScatterMode = false;
//        }
//        if (!isScatterMode && currentTime - lastChaseTime >= CHASE_INTERVAL) {
//
//            scatterMode();
//            lastScatterTime = currentTime;
//            isScatterMode = true;
//        }
        System.out.println("target: " + this.getClass() + ": " + this.target.x + "," + this.target.y);
        //   eatenMode();
        move();
    }

    public void move() {
        // Update position
        if (isStart) {
            getShortWay();
            // Update direction based on sortWay
            if (sortWay.x > point.x) {
                direction = "right";
            } else if (sortWay.x < point.x) {
                direction = "left";
            } else if (sortWay.y > point.y) {
                direction = "down";
            } else if (sortWay.y < point.y) {
                direction = "up";
            }
            isStart = false;
        } else {
            cuntPixel += speed;
            if (cuntPixel >= tileSize) {
                point.setLocation(sortWay);
                cuntPixel = 0;
                // Update sprite animation
                spriteNum = spriteNum == 1 ? 2 : 1;
                isStart = true;
            }
        }
        //System.out.println(point.x + " " + point.y + "this cunt pixel " + cuntPixel);
    }

    public String getCurrentPosition() {//function that return the  current direction
        return direction;
    }

    public void getShortWay() {
        //  behaviorIfInCage();
        List<Point> direction = collisionChecker.getDirection(this);
        Ghost.this.sortWay = direction.getFirst();
        double minDistance = Ghost.this.sortWay.distance(target);
        for (int i = 1; i < direction.size(); i++) {
            if (direction.get(i).distance(target) < minDistance) {
                Ghost.this.sortWay = direction.get(i);
                minDistance = Ghost.this.sortWay.distance(target);
            }
        }
    }

    public void behaviorIfInCage() {
        //need to update the target after it finish !!!!
        if (point.y == 14 || point.y == 13 && point.x >= 9 && point.x <= 15) {
            target = new Point(15, 12);
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        screenX = point.x * tileSize;
        screenY = point.y * tileSize;

        // Normal ghost sprites based on direction
        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                screenY -= cuntPixel;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                screenY += cuntPixel;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                screenX -= cuntPixel;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                screenX += cuntPixel;
                break;
            default:
                image = right1;
        }
        if (isFrightenedMode) {
            image = frightenedImage;
        }
        if (isEatenMode) {
            switch (direction) {
                case "up":
                    image = up;
                    break;
                case "down":
                    image = down;
                    break;
                case "left":
                    image = left;
                    break;
                case "right":
                    image = right;
                    break;
                default:
                    image = right;
            }
        }
        if (image != null) {
            g2.drawImage(image, screenX, screenY, tileSize - 6, tileSize - 6, null);
        }
    }

    public void getFrightenedImage() {
        try {
            String path = "/resources/image/imageEntity/imageGhosts/ImageFrightened/f1.png";
            frightenedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            System.out.println("can't load image");
            e.printStackTrace();
        }
    }

    public void getEatenImage() {
        try {
            String path = "/resources/image/imageEntity/imageGhosts/ImageEaten/";
            up = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "up.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "down.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "left.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "right.png")));
        } catch (IOException e) {
            System.out.println("can't load image");
            e.printStackTrace();
        }
    }
}
