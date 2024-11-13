package control;

import model.entity.Entity;
import view.GamePanel;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gp) {
        this.gamePanel = gp;
    }


    public void checkTile(Entity entity) {
        int nextTile;
        switch (entity.direction) {
            case "up":
                nextTile = (gamePanel.tileManager.mapTiles[entity.positionX][entity.positionY - 1]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                nextTile = (gamePanel.tileManager.mapTiles[entity.positionX][entity.positionY + 1]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                nextTile = (gamePanel.tileManager.mapTiles[entity.positionX - 1][entity.positionY]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                nextTile = (gamePanel.tileManager.mapTiles[entity.positionX + 1][entity.positionY]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
