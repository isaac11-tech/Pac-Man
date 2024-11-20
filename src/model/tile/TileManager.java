package model.tile;

import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public static Tile[] typeTiles;//array of types of tile
    public static int[][] mapTiles;// matrix that presents game panel

    public TileManager(GamePanel gp) {
        this.gp = gp;
        typeTiles = new Tile[17];
        mapTiles = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/resources/map/map01.txt");//good
    }

    public boolean getTileImage() {
        try {
            // Use consistent path case and add error checking
            for (int i = 0; i < 17; i++) {
                typeTiles[i] = new Tile();
                String imagePath = String.format("/resources/image/imageTiles/%d.png", i);
                InputStream is = getClass().getResourceAsStream(imagePath);

                if (is == null) {
                    System.err.printf("Could not find image resource: %s%n", imagePath);
                    return false;
                }

                typeTiles[i].image = ImageIO.read(is);
                if (i != 1 && i != 16) {//not ready
                    typeTiles[i].collision = true;//reset all the tiles to true except the black tile
                }
                is.close();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error loading tile images: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public void loadMap(String filePath) {//function that load from file to matrix (type of tile)
        try (InputStream getPath = getClass().getResourceAsStream(filePath); BufferedReader br = new BufferedReader(new InputStreamReader(getPath))) {

            if (getPath == null) {
                throw new FileNotFoundException("Map file not found: " + filePath);
            }

            int row = 0;

            while (row < gp.maxScreenRow) {
                String line = br.readLine();
                if (line == null) break;  // End of file reached

                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTiles[col][row] = num;
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {//function that draw the tile on game panel
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTiles[col][row];//check what type we need to draw

            g2.drawImage(typeTiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
