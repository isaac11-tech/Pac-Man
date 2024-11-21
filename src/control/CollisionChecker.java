package control;

import model.entity.Entity;
import model.entity.Ghost;
import view.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.tile.TileManager.mapTiles;
import static model.tile.TileManager.typeTiles;

//don't forget do map static
public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gp) {
        this.gamePanel = gp;
    }


    public void checkTile(Entity entity) {
        int nextTile;
        switch (entity.direction) {
            case "up":
                nextTile = mapTiles[entity.point.x][entity.point.y - 1];
                if (typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                nextTile = (mapTiles[entity.point.x][entity.point.y + 1]);
                if (typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                nextTile = (mapTiles[entity.point.x - 1][entity.point.y]);
                if (typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                nextTile = (mapTiles[entity.point.x + 1][entity.point.y]);
                if (typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public List<Point> getDirection(Ghost ghost) {
        List<Point> direction = new ArrayList<>();
        String currPoz = ghost.getCurrentPosition();
        int nextTile;
        

        //up
        nextTile = (mapTiles[ghost.point.x][ghost.point.y - 1]);
        if (!typeTiles[nextTile].collision && !currPoz.equals("down")) {
            direction.add(new Point(ghost.point.x, ghost.point.y - 1));
        }
        //right
        nextTile = (mapTiles[ghost.point.x + 1][ghost.point.y]);
        if (!typeTiles[nextTile].collision && !currPoz.equals("left")) {
            direction.add(new Point(ghost.point.x + 1, ghost.point.y));
        }
        //down
        nextTile = (mapTiles[ghost.point.x][ghost.point.y + 1]);
        if (!typeTiles[nextTile].collision && !currPoz.equals("up")) {
            direction.add(new Point(ghost.point.x, ghost.point.y + 1));
        }
        //left
        nextTile = (mapTiles[ghost.point.x - 1][ghost.point.y]);
        if (!typeTiles[nextTile].collision && !currPoz.equals("right")) {
            direction.add(new Point(ghost.point.x - 1, ghost.point.y));
        }
        return direction;
    }
}
