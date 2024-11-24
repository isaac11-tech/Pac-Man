package model.entity;

import control.CollisionChecker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Inky extends Ghost {
    public Inky(CollisionChecker collisionChecker, PacManPlayer pacManPlayer) {
        super(collisionChecker, pacManPlayer);
    }

    @Override
    public void chaseMode() {

    }

    @Override
    public void scatterMode() {

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


    @Override
    void setBasePosition() {

    }

    @Override
    Point getBasePosition() {
        return null;
    }

    @Override
    void setScatterPosition() {

    }

    @Override
    Point getScatterPosition() {
        return null;
    }

    @Override
    public void update() {

    }
}
