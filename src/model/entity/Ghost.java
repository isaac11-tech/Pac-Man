package model.entity;

import control.CollisionChecker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static view.GamePanel.*;

abstract public class Ghost extends Entity {
    PacManPlayer pacManPlayer;
    CollisionChecker collisionChecker;
    Point target;
    Point sortWay;
    Point scatterPosition;
    int cuntPixel = 0;
    boolean isStart = true;
    String direction = "up";
    int x;
    int y;


    public Ghost(CollisionChecker collisionChecker, PacManPlayer pacManPlayer) {
        this.pacManPlayer = pacManPlayer;
        this.collisionChecker = collisionChecker;
        this.point = new Point(0, 0);
        this.target = new Point(0, 0);
        this.sortWay = new Point(0, 0);

    }

    abstract void setBasePosition();

    abstract Point getBasePosition();

    abstract void setScatterPosition();

    abstract Point getScatterPosition();

    abstract public void scatterMode();

    abstract void chaseMode();//pass

    public void frightenedMode() {
        int randomX = (int) (Math.random() * screenWidth);
        int randomY = (int) (Math.random() * screenHigh);
        this.target.setLocation(randomX, randomY);
        this.speed = 1;
    }

    public void eatenMode() {
        this.target.setLocation(getBasePosition());
        //need to change the Image and speed
    }

    public void update() {
       // scatterMode();
        //frightenedMode();
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
        if (image != null) {
            g2.drawImage(image, screenX, screenY, tileSize - 6, tileSize - 6, null);
        }
    }
}
