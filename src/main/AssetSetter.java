package main;

import entity.Enemy;
import object.OBJ_Boots;
import object.OBJ_Exit;
import object.OBJ_Money;
import java.util.concurrent.ThreadLocalRandom;
import tile.TileManager;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setInitObject(){
        setRandomMoney(0);

        gp.obj[1] = new OBJ_Exit();
        gp.obj[1].x = 13 * gp.tileSize;
        gp.obj[1].y = 8 * gp.tileSize;
        gp.obj[1].hitBox.x = gp.obj[1].x;
        gp.obj[1].hitBox.y = gp.obj[1].y;

        gp.obj[2] = new OBJ_Boots();
        gp.obj[2].x = 19 * gp.tileSize;
        gp.obj[2].y = 10 * gp.tileSize;
        gp.obj[2].hitBox.x = gp.obj[2].x;
        gp.obj[2].hitBox.y = gp.obj[2].y;

        setEnemy(0);

    }

    public void setRandomMoney(int i){
        int min = 0;
        int randomNumCol = ThreadLocalRandom.current().nextInt(min, gp.maxScreenCol-1 + 1);
        int randomNumRow = ThreadLocalRandom.current().nextInt(min, gp.maxScreenRow-1 + 1);


        while (gp.tileM.mapTileData[randomNumCol][randomNumRow] != 0){
            randomNumCol = ThreadLocalRandom.current().nextInt(min, gp.maxScreenCol-1 + 1);
            randomNumRow = ThreadLocalRandom.current().nextInt(min, gp.maxScreenRow-1 + 1);
        }

        gp.obj[i] = new OBJ_Money();
        gp.obj[i].x = randomNumCol * gp.tileSize;
        gp.obj[i].y = randomNumRow * gp.tileSize;
        gp.obj[i].hitBox.x = gp.obj[i].x;
        gp.obj[i].hitBox.y = gp.obj[i].y;
    }


    public void spawningEnemy(){
        if(gp.timer < gp.spawnTime && gp.enemyCounter < gp.enemies.length){
            gp.spawnTime = gp.timer - 6;
            setEnemy(gp.enemyCounter);
            gp.enemyCounter++;
        }
    }

    public void setEnemy(int i){
        gp.enemies[i] = new Enemy(gp);
    }
}
