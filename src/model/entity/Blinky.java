package model.entity;

import control.CollisionChecker;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Blinky extends Ghost {
    public Blinky(CollisionChecker collisionChecker, PacManPlayer pacManPlayer) {
        super(collisionChecker, pacManPlayer);
        getBlinkyImage();
        setBasePosition();
        chaseMode();
    }

    public void getBlinkyImage() {
        try {
            String path = "/resources/image/imageEntity/imageGhosts/blinky/";
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
        point.setLocation(18, 14);
        speed = 2;
        target.setLocation(19, 12);
    }

    @Override
    public void chaseMode() {
        target = pacManPlayer.point;
        super.chaseMode();
    }
}
