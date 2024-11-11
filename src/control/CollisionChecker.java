package control;

import model.entity.Entity;
import view.GamePanel;

public class CollisionChecker {
    GamePanel gamePanel;

    CollisionChecker(GamePanel gp) {
        this.gamePanel = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.screenX + entity.solidArea.x;
        int entityRightX = entity.screenX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.screenY + entity.solidArea.y;
        int entityBottomY = entity.screenY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gamePanel.tileSize;
        int entityRightCol = entityRightX / gamePanel.tileSize;
        int entityTopRow = entityTopY / gamePanel.tileSize;
        int entityBottomRow = entityBottomY / gamePanel.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTiles[entityLeftCol][entityTopRow]-1;
                tileNum2 = gamePanel.tileManager.mapTiles[entityRightCol][entityTopRow]-1;
                if (gamePanel.tileManager.typeTiles[tileNum1].collision == true||gamePanel.tileManager.typeTiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                    break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTiles[entityLeftCol][entityBottomRow]-1;
                tileNum2 = gamePanel.tileManager.mapTiles[entityRightCol][entityBottomRow]-1;
                if (gamePanel.tileManager.typeTiles[tileNum1].collision == true||gamePanel.tileManager.typeTiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTiles[entityLeftCol][entityTopRow]-1;
                tileNum2 = gamePanel.tileManager.mapTiles[entityRightCol][entityBottomRow]-1;
                if (gamePanel.tileManager.typeTiles[tileNum1].collision == true||gamePanel.tileManager.typeTiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTiles[entityRightCol][entityTopRow]-1;
                tileNum2 = gamePanel.tileManager.mapTiles[entityRightCol][entityBottomRow]-1;
                if (gamePanel.tileManager.typeTiles[tileNum1].collision == true||gamePanel.tileManager.typeTiles[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
