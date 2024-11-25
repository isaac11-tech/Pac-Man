package model.objects;

import view.GamePanel;

import java.awt.*;

import static model.tile.TileManager.mapTiles;
import static view.GamePanel.*;

public class SuperCoin extends SuperObjects{
    GamePanel gamePanel;
    public static boolean[][] mapSuperCoin;
    int sizeCoin;
    public final int coinValue = 50;


    public SuperCoin(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mapSuperCoin = new boolean[maxScreenCol][maxScreenRow];
        this.sizeCoin = tileSize;
        loadMap();
        getCoinImage();
    }

    public void loadMap() {
        for (int i = 0; i < maxScreenCol; i++) {
            for (int j = 0; j < maxScreenRow; j++) {
                if (mapTiles[i][j] == 16) {
                    mapSuperCoin[i][j] = true;
                } else {
                    mapSuperCoin[i][j] = false;
                }
            }
        }
    }

    public void getCoinImage() {
        getObjectImage("/resources/image/objects/coins.png");
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < mapSuperCoin.length; i++) {
            for (int j = 0; j < mapSuperCoin[0].length; j++) {
                if (mapSuperCoin[i][j]) {
                    int x = i * tileSize ;
                    int y = j * tileSize ;
                    g2.drawImage(image, x, y, sizeCoin, sizeCoin, null);
                }
            }
        }
    }
}
