package Control;

import View.GamePanel;

import java.awt.*;

public class GameRunnable extends GamePanel implements Runnable {
    int FPS = 60;

    Thread gameThread;//create object thread for running the game

    //public Player player = new Player(this, keyH);!!!
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        //player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        // player.draw(g2);
        g2.dispose();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;// 0.16 for second
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();//

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
