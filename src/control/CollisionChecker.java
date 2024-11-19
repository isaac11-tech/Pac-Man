package control;

import model.entity.Entity;
import view.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//dont forget do map static
public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gp) {
        this.gamePanel = gp;
    }


    public void checkTile(Entity entity) {
        int nextTile;
        switch (entity.direction) {
            case "up":
                nextTile = (gamePanel.tileManager.mapTiles[entity.point.x][entity.point.y - 1]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                nextTile = (gamePanel.tileManager.mapTiles[entity.point.x][entity.point.y + 1]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                nextTile = (gamePanel.tileManager.mapTiles[entity.point.x - 1][entity.point.y]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                nextTile = (gamePanel.tileManager.mapTiles[entity.point.x + 1][entity.point.y]);
                if (gamePanel.tileManager.typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public List<Point> getDirection(Entity entity) {
        List<Point> direction = new ArrayList<>();
        int nextTile;
        //up
        nextTile = (gamePanel.tileManager.mapTiles[entity.point.x][entity.point.y - 1]);
        if (!gamePanel.tileManager.typeTiles[nextTile].collision) {
            direction.add(new Point(entity.point.x, entity.point.y - 1));
        }
        //right
        nextTile = (gamePanel.tileManager.mapTiles[entity.point.x + 1][entity.point.y]);
        if (!gamePanel.tileManager.typeTiles[nextTile].collision) {
            direction.add(new Point(entity.point.x + 1, entity.point.y));
        }
        //down
        nextTile = (gamePanel.tileManager.mapTiles[entity.point.x][entity.point.y + 1]);
        if (!gamePanel.tileManager.typeTiles[nextTile].collision) {
            direction.add(new Point(entity.point.x, entity.point.y + 1));
        }
        //left
        nextTile = (gamePanel.tileManager.mapTiles[entity.point.x - 1][entity.point.y]);
        if (!gamePanel.tileManager.typeTiles[nextTile].collision) {
            direction.add(new Point(entity.point.x - 1, entity.point.y));
        }
        return direction;
    }
}
