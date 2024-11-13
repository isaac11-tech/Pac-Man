package model.objects;

import view.GamePanel;

public class Coin extends SuperObjects {
    GamePanel gamePanel;

    Coin(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    boolean[][] mapCoin = new boolean[gamePanel.maxScreenCol][gamePanel.maxScreenRow];

    public void loadMap() {
        for (int i = 0; i < gamePanel.maxScreenCol; i++) {
            for (int j = 0; j < gamePanel.maxScreenRow; j++) {
                if (gamePanel.tileManager.mapTiles[i][j] == 1) {
                    mapCoin[i][j] = true;
                }
            }
        }
    }

}
