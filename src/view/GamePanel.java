package view;

import control.KeyHandler;
import model.entity.PacManPlayer;
import model.tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {
    public final int originalTileSize = 6;
    public final int scale = 4;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 31;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHigh = tileSize * maxScreenRow;

    Image icon;
    KeyHandler keyHandler;
    TileManager tileManager;
    PacManPlayer pacManPlayer;

    public GamePanel() {
        loadIcon();
        setupWindow();
    }

    private void loadIcon() {
        try {
            icon = ImageIO.read(new File("src/Resources/Image/imageEntity/ICON1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pac-Man");
        window.setIconImage(icon);

        this.setPreferredSize(new Dimension(screenWidth, screenHigh));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void setComponents(KeyHandler keyHandler, TileManager tileManager, PacManPlayer pacManPlayer) {
        this.keyHandler = keyHandler;
        this.tileManager = tileManager;
        this.pacManPlayer = pacManPlayer;
        this.addKeyListener(keyHandler); // Add KeyListener after keyHandler is set
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (tileManager != null) {
            tileManager.draw(g2);
        }
        if (pacManPlayer != null) {
            pacManPlayer.draw(g2);
        }

        g2.dispose();
    }
}