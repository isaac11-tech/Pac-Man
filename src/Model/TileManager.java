package Model;

import View.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] typeTiles;//array of types of tile
    int[][] mapTiles;// matrix that presents game panel

    public TileManager(GamePanel gp) {
        this.gp = gp;
        typeTiles = new Tile[10];
        mapTiles = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();//"C:\Users\ADMIN\IdeaProjects\Pac-Man\src\View\Resources\Map\Pac-Man map1.txt"
        loadMap("/View/Resources/Map/Pac-Man map1.txt");//good
    }

    public void getTileImage() {//function that defines type of Tiles from image
        try {
            typeTiles[0] = new Tile();
            typeTiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/View/Resources/Image/water.png")));

            typeTiles[1] = new Tile();
            typeTiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/View/Resources/Image/wall.png")));

        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace(); // or log the error for better debugging
        }
    }


    public void draw(Graphics2D g2) {//function that draw the tile on game panel
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTiles[col][row];//check what type we need to draw

            g2.drawImage(typeTiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);//!!!!!not ready yet
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
