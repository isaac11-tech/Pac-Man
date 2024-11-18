package model.objects;

import view.GamePanel;

import java.awt.*;

public class SuperCoin extends SuperObjects{
    GamePanel gamePanel;
    public boolean[][] mapCoin;
    int sizeCoin;


    public SuperCoin(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapCoin = new boolean[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        this.sizeCoin = gamePanel.tileSize;
        loadMap();
        getCoinImage();
    }

    public void loadMap() {
        for (int i = 0; i < gamePanel.maxScreenCol; i++) {
            for (int j = 0; j < gamePanel.maxScreenRow; j++) {
                if (gamePanel.tileManager.mapTiles[i][j] == 16) {
                    mapCoin[i][j] = true;
                } else {
                    mapCoin[i][j] = false;
                }
                System.out.println(mapCoin[i][j]);
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
                    int x = i * gamePanel.tileSize ;
                    int y = j * gamePanel.tileSize ;
                    g2.drawImage(image, x, y, sizeCoin, sizeCoin, null);
                }
            }
        }
    }
}
