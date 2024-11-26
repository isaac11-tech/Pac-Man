package model.entity;

import control.CollisionChecker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static view.GamePanel.*;
import static view.GamePanel.tileSize;

public class Inky extends Ghost {
    public Inky(CollisionChecker collisionChecker, PacManPlayer pacManPlayer) {
        super(collisionChecker, pacManPlayer);
        getInkyImage();
        setBasePosition();
        setScatterPosition();
    }

    @Override
    void setBasePosition() {
        x = 11;
        y = 14;
        this.point.setLocation(x, y);
        speed = 2;
    }

    @Override
    public void chaseMode() {
        int randomX = (int) (Math.random() * (screenWidth / tileSize - 1));// not fixed !!!!!!!!!
        int randomY = (int) (Math.random() * (screenHigh / tileSize - 1));
        this.target = new Point(randomX, randomY);
    }

    @Override
    public void scatterMode() {
        target = new Point(getScatterPosition());
    }

    @Override
    Point getBasePosition() {
        return new Point(x, y);
    }

    @Override
    void setScatterPosition() {
        scatterPosition = new Point(1, 27);
    }

    @Override
    Point getScatterPosition() {
        if (scatterPosition == null) {
            System.out.println("scatterPosition is null");
        }
        return scatterPosition;
    }

    public void getInkyImage() {
        try {
            String path = "/resources/image/imageEntity/imageGhosts/inky/";
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path + "right2.png")));
        } catch (IOException e) {
            System.out.println("can't load image");
            e.printStackTrace();
        }
    }
}
