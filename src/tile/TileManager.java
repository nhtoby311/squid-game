package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileData;
    private final int ARRAY_MEMORY_SIZE = 100;


    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileData = new int[ARRAY_MEMORY_SIZE][ARRAY_MEMORY_SIZE];
        System.out.println(gp.maxScreenRow);
        System.out.println(gp.maxScreenCol);

        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall1.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[3].collision = true;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/map/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            for(int row = 0; row < gp.maxScreenRow; row++){
                String line = br.readLine();
                String numbers[] = line.split(" ");

                for(int col = 0; col < gp.maxScreenCol; col++){
                    int num = Integer.parseInt(numbers[col]);


                    mapTileData[col][row] = num;
                }
            }


            br.close();

        }catch(Exception e){

        }
    }



    public void draw(Graphics2D g2){

        int x = 0;
        int y = 0;

        for(int row = 0; row < gp.maxScreenRow; row++){
            for(int col = 0; col < gp.maxScreenCol; col++){
                int tileNum = mapTileData[col][row];

                g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null);
                x+= gp.tileSize;
            }
            x = 0;
            y += gp.tileSize;
        }


    }

}
