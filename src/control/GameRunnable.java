package control;

import model.entity.PacManPlayer;
import model.objects.Coin;
import model.objects.SuperCoin;
import model.tile.TileManager;
import view.GamePanel;

public class GameRunnable implements Runnable {
    Thread gameThread;//create object thread for running the game
    KeyHandler keyHandler;
    GamePanel gamePanel;
    TileManager tileManager;
    Coin coin;
    SuperCoin superCoin;
    PacManPlayer pacManPlayer;
    CollisionChecker collisionChecker;

    int FPS = 60;

    public GameRunnable() {
        this.keyHandler = new KeyHandler();
        this.gamePanel = new GamePanel();
        this.tileManager = new TileManager(gamePanel);
        this.gamePanel.tileManager = this.tileManager;
        this.coin = new Coin(gamePanel);
        this.superCoin = new SuperCoin(gamePanel);
        this.pacManPlayer = new PacManPlayer(gamePanel, keyHandler,coin,superCoin);
        this.collisionChecker = new CollisionChecker(gamePanel);
        this.gamePanel.setComponents(keyHandler, tileManager,coin,superCoin, pacManPlayer, collisionChecker);
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
