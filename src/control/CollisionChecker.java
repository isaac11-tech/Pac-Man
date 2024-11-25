package control;

import model.entity.Entity;
import model.entity.Ghost;
import model.entity.PacManPlayer;
import view.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static model.tile.TileManager.mapTiles;
import static model.tile.TileManager.typeTiles;

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
                if (entity.point.x <= 0) {
                    entity.collisionOn = true;
                    break;
                }
                nextTile = (mapTiles[entity.point.x - 1][entity.point.y]);
                if (typeTiles[nextTile].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                if (entity.point.x >= GamePanel.maxScreenCol - 1) {
                    entity.collisionOn = true;
                    break;
                }
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
        // up
        if (ghost.point.y > 0) {
            nextTile = (mapTiles[ghost.point.x][ghost.point.y - 1]);
            if (!typeTiles[nextTile].collision && !currPoz.equals("down")) {
                direction.add(new Point(ghost.point.x, ghost.point.y - 1));
            }
        }

        // right
        if (ghost.point.x < GamePanel.maxScreenCol - 1) {
            nextTile = (mapTiles[ghost.point.x + 1][ghost.point.y]);
            if (!typeTiles[nextTile].collision && !currPoz.equals("left")) {
                direction.add(new Point(ghost.point.x + 1, ghost.point.y));
            }
        }
        // down
        if (ghost.point.y < GamePanel.maxScreenRow - 1) {
            nextTile = (mapTiles[ghost.point.x][ghost.point.y + 1]);
            if (!typeTiles[nextTile].collision && !currPoz.equals("up")) {
                direction.add(new Point(ghost.point.x, ghost.point.y + 1));
            }
        }

        // left
        if (ghost.point.x > 0) {
            nextTile = (mapTiles[ghost.point.x - 1][ghost.point.y]);
            if (!typeTiles[nextTile].collision && !currPoz.equals("right")) {
                direction.add(new Point(ghost.point.x - 1, ghost.point.y));
            }
        }

        if (direction.isEmpty()) {
            System.out.println("not way to go");
            if (currPoz.equals("up")) {
                direction.add(new Point(ghost.point.x, ghost.point.y + 1));
            } else if (currPoz.equals("down")) {
                direction.add(new Point(ghost.point.x, ghost.point.y - 1));
            } else if (currPoz.equals("left")) {
                direction.add(new Point(ghost.point.x + 1, ghost.point.y));
            } else if (currPoz.equals("right")) {
                direction.add(new Point(ghost.point.x - 1, ghost.point.y));
            }
        }
        return direction;
    }
    public boolean collisionPacManWithGhost(PacManPlayer pacManPlayer ,Ghost ghost){
        return pacManPlayer.point.equals(ghost.point);
    }
}
