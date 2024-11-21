package model.entity;

import control.CollisionChecker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static view.GamePanel.tileSize;

abstract public class Ghost extends Entity {
    PacManPlayer pacManPlayer;
    CollisionChecker collisionChecker;
    Point target;
    Point sortWay;
    Point prevPoint;
    int cuntPixel;
    String direction = "up";


    public Ghost(CollisionChecker collisionChecker, PacManPlayer pacManPlayer) {
        this.pacManPlayer = pacManPlayer;
        this.collisionChecker = collisionChecker;
        this.point = new Point();
        this.target = new Point();
        this.sortWay = new Point();

    }

    abstract public void chaseMode();

    abstract public void scatterMode();

    public void frightenedMode() {

    }

    public void eatenMode() {

    }

    public void update() {
        chaseMode();
        move();
    }

    ;

    abstract void setBasePosition();

    abstract void setScatterPosition();

    public void move() {
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

        // Update position
        cuntPixel += speed;
        if (cuntPixel >= tileSize) {
            point.setLocation(sortWay);
            cuntPixel = 0;

            // Update sprite animation
            spriteNum = spriteNum == 1 ? 2 : 1;
        }
    }

    public String getCurrentPosition() {//function that return the  current direction
        return direction;
    }

    public void getShortWay() {
        List<Point> direction = collisionChecker.getDirection(this);
        if (direction.isEmpty()) {
            sortWay = new Point(point);
            System.out.println("is not way to ghost");
            return;
        }

        Ghost.this.sortWay = direction.getFirst();
        double minDistance = Ghost.this.sortWay.distance(target);
        for (int i = 1; i < direction.size(); i++) {
            if (direction.get(i).distance(target) < minDistance) {
                Ghost.this.sortWay = direction.get(i);
                minDistance = Ghost.this.sortWay.distance(target);
            }
        }
        System.out.println(sortWay.x + " " + sortWay.y);
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
