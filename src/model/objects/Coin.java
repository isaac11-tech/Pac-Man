package model.objects;

import view.GamePanel;

import java.awt.*;

public class Coin extends SuperObjects {
    GamePanel gamePanel;
    public boolean[][] mapCoin;
    int sizeCoin;
    public final int coinValue = 10;


    public Coin(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapCoin = new boolean[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        this.sizeCoin = gamePanel.tileSize / 2;
        loadMap();
        getCoinImage();
    }

    public void loadMap() {
        for (int i = 0; i < gamePanel.maxScreenCol; i++) {
            for (int j = 0; j < gamePanel.maxScreenRow; j++) {
                if (gamePanel.tileManager.mapTiles[i][j] == 1) {
                    mapCoin[i][j] = true;
                } else {
                    mapCoin[i][j] = false;
                }
            }
        }
    }

    public void getCoinImage() {
        getObjectImage("/resources/image/objects/coins.png");
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < mapCoin.length; i++) {
            for (int j = 0; j < mapCoin[0].length; j++) {
                if (mapCoin[i][j]) {
                    int x = i * gamePanel.tileSize + (gamePanel.tileSize - sizeCoin) / 2;
                    int y = j * gamePanel.tileSize + (gamePanel.tileSize - sizeCoin) / 2;
                    g2.drawImage(image, x, y, sizeCoin, sizeCoin, null);
                }
            }
        }
    }
}
