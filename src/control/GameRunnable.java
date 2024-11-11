package control;

import model.entity.PacManPlayer;
import model.tile.TileManager;
import view.GamePanel;

public class GameRunnable implements Runnable {
    int FPS = 60;
    Thread gameThread;//create object thread for running the game
    KeyHandler keyHandler;
    GamePanel gamePanel;
    TileManager tileManager;
    PacManPlayer pacManPlayer;
    CollisionChecker collisionChecker;

    public GameRunnable() {
        this.keyHandler = new KeyHandler();
        this.gamePanel = new GamePanel();
        this.tileManager = new TileManager(gamePanel);
        this.pacManPlayer = new PacManPlayer(gamePanel, keyHandler);
        this.collisionChecker = new CollisionChecker(gamePanel);
        this.gamePanel.setComponents(keyHandler, tileManager, pacManPlayer,collisionChecker);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        pacManPlayer.update();
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
