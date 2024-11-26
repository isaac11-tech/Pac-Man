package model.entity;

import control.CollisionChecker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Pinky extends Ghost {
    public Pinky(CollisionChecker collisionChecker, PacManPlayer pacManPlayer) {
        super(collisionChecker, pacManPlayer);
        getPinkyImage();
        setBasePosition();
        setScatterPosition();
    }

    @Override
    void setBasePosition() {
        x = 14;
        y = 14;
        this.point.setLocation(x, y);
        speed = 2;
    }

    @Override
    public void chaseMode() {
        if (pacManPlayer.direction.equals("up")) {
            target.x = pacManPlayer.getPacManPoz().x;
            target.y = pacManPlayer.getPacManPoz().y - 4;
        }
        if (pacManPlayer.direction.equals("down")) {
            target.x = pacManPlayer.getPacManPoz().x;
            target.y = pacManPlayer.getPacManPoz().y + 4;
        }
        if (pacManPlayer.direction.equals("left")) {
            target.x = pacManPlayer.getPacManPoz().x - 4;
            target.y = pacManPlayer.getPacManPoz().y;
        }
        if (pacManPlayer.direction.equals("right")) {
            target.x = pacManPlayer.getPacManPoz().x + 4;
            target.y = pacManPlayer.getPacManPoz().y;
        }
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
        scatterPosition = new Point(23, 27);
    }

    @Override
    Point getScatterPosition() {
        if (scatterPosition == null) {
            System.out.println("scatterPosition is null");
        }
        return scatterPosition;
    }

    public void getPinkyImage() {
        try {
            String path = "/resources/image/imageEntity/imageGhosts/pinky/";
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
