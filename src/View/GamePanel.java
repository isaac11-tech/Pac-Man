package View;

import Control.KeyHandler;
import Model.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel  {
    //tile size and screen size sitings
    public final int originalTileSize = 6;//size of 16*16
    public final int scale = 4;
    public final int tileSize = originalTileSize * scale; // size of 48*48
    public final int maxScreenCol = 25;
    public final int maxScreenRow = 31;
    public final int screenWidth = tileSize * maxScreenCol; // size of 768
    public final int screenHigh = tileSize * maxScreenRow; //size of 576

    Image icon;

    {
        try {
            icon = ImageIO.read(new File("src/View/Resources/Image/ICON1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    KeyHandler keyHandler = new KeyHandler();
    public TileManager tileManager = new TileManager(this);

    public GamePanel() {
        //create window for game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pac-Man");
        window.setIconImage(icon);
        this.setPreferredSize(new Dimension(screenWidth, screenHigh));// sitings of size
        this.setBackground(Color.black);// sitings of backRound
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);// sitings of keyBord
        this.setFocusable(true);
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }


}
