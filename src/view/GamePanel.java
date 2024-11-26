package view;

import control.CollisionChecker;
import control.KeyHandler;
import model.entity.*;
import model.objects.Coin;
import model.objects.SuperCoin;
import model.tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    public static final int tileSize = 25;
    public static final int maxScreenCol = 25;
    public static final int maxScreenRow = 31;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHigh = tileSize * maxScreenRow;

    Image icon;
    KeyHandler keyHandler;
    public TileManager tileManager;
    public Coin coin;
    SuperCoin superCoin;
    PacManPlayer pacManPlayer;
    public CollisionChecker collisionChecker;
    Blinky blinky;
    Clyde clyde;
    Inky inky;
    Pinky pinky;

    public GamePanel() {
        loadIcon();
        setupWindow();
    }

    private void loadIcon() {
        try {
            icon = ImageIO.read(new File("src/resources/image/objects/ICON1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawScore(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));

        g2.drawString("SCORE: " + pacManPlayer.cuntScore, 3 * tileSize, 2 * tileSize);


        String highScoreText = "HIGH SCORE: " + pacManPlayer.getHighScore();
        g2.drawString(highScoreText, 9 * tileSize, 2 * tileSize);

        String level = "LEVEL : 1";
        g2.drawString(level, 18 * tileSize, 2 * tileSize);
    }

    public void drawLives(Graphics2D g2) {//need to chane!!!!!!!!!!!!!!!
        g2.setColor(Color.YELLOW);
        int lifeSize = 20;
        int spacing = 25;
        int x = 3 * tileSize;
        int y = 30;
        int lives = 3;
        for (int i = 0; i < lives; i++) {
            g2.fillArc(x + (i * spacing), y, lifeSize, lifeSize, 30, 300);
        }
    }

    private void setupWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pac-Man");
        window.setIconImage(icon);

        this.setPreferredSize(new Dimension(screenWidth, screenHigh));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void setComponents(KeyHandler keyHandler, TileManager tileManager, Coin coin, SuperCoin superCoin, PacManPlayer pacManPlayer, CollisionChecker collisionChecker, Blinky blinky,Clyde clyde,Inky inky,Pinky pinky) {
        this.keyHandler = keyHandler;
        this.tileManager = tileManager;
        this.coin = coin;
        this.superCoin = superCoin;
        this.pacManPlayer = pacManPlayer;
        this.collisionChecker = collisionChecker;
        this.addKeyListener(keyHandler); // Add KeyListener after keyHandler is set
        this.blinky = blinky;
        this.clyde = clyde;
        this.inky = inky;
        this.pinky = pinky;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (tileManager != null) {
            tileManager.draw(g2);
        }
        if (coin != null) {
            coin.draw(g2);
        }
        if (superCoin != null) {
            superCoin.draw(g2);
        }
        if (pacManPlayer != null) {
            pacManPlayer.draw(g2);
            drawScore(g2);
            drawLives(g2);
        }
        if (blinky != null) {
            blinky.draw(g2);
        }
        if (clyde != null) {
            clyde.draw(g2);
        }
        if (inky != null) {
            inky.draw(g2);
        }
        if (pinky != null) {
            pinky.draw(g2);
        }
        g2.dispose();
    }
}