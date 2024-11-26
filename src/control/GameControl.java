package control;

import model.entity.*;
import model.objects.Coin;
import model.objects.SuperCoin;
import model.tile.TileManager;
import view.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameControl implements Runnable {
    Thread gameThread;//create object thread for running the game
    KeyHandler keyHandler;
    GamePanel gamePanel;
    TileManager tileManager;
    Coin coin;
    SuperCoin superCoin;
    PacManPlayer pacManPlayer;
    CollisionChecker collisionChecker;
    Blinky blinky;
    Clyde clyde;
    Inky inky;
    Pinky pinky;

    int FPS = 60;

    public GameControl() {
        this.keyHandler = new KeyHandler();
        this.gamePanel = new GamePanel();
        this.tileManager = new TileManager(gamePanel);
        this.gamePanel.tileManager = this.tileManager;
        this.coin = new Coin(gamePanel);
        this.superCoin = new SuperCoin(gamePanel);
        this.pacManPlayer = new PacManPlayer(gamePanel, keyHandler, coin, superCoin);
        this.collisionChecker = new CollisionChecker(gamePanel);
        this.blinky = new Blinky(collisionChecker, pacManPlayer);
        this.clyde = new Clyde(collisionChecker, pacManPlayer);
        this.inky = new Inky(collisionChecker, pacManPlayer);
        this.pinky = new Pinky(collisionChecker, pacManPlayer);
        this.gamePanel.setComponents(keyHandler, tileManager, coin, superCoin, pacManPlayer, collisionChecker, blinky,clyde,inky,pinky);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        pacManPlayer.update();
        blinky.update();
        clyde.update();
        pinky.update();
        inky.update();
    }


    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;// 0.16 for second
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            gamePanel.repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
