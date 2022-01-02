package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftX = entity.hitBox.x;
        int entityRightX = entity.hitBox.x + entity.hitBox.width;
        int entityTopY = entity.hitBox.y;
        int entityBotY = entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftX/gp.tileSize;
        int entityRightCol = entityRightX/gp.tileSize;
        int entityTopRow = entityTopY/gp.tileSize;
        int entityBotRow = entityBotY/gp.tileSize;

        int tileNum1,tileNum2;


        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed)/gp.tileSize;
                //System.out.println("entityTopRow" + entityTopRow);
                tileNum1 = gp.tileM.mapTileData[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileData[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionable = true;
                }
                break;
            case "down":
                entityBotRow = (int)Math.round((double)(entityBotY - entity.speed -16)/gp.tileSize);            //For not stucking in the wall :/
                //System.out.println("entityBotRow" + entityBotRow);
                tileNum1 = gp.tileM.mapTileData[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileM.mapTileData[entityRightCol][entityBotRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionable = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileData[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileData[entityLeftCol][entityBotRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionable = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/gp.tileSize;
                //System.out.println("entityRightCol" + entityRightCol);
                tileNum1 = gp.tileM.mapTileData[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileData[entityRightCol][entityBotRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionable = true;
                }
                break;
        }
    }


    public int checkObject(Entity entity, boolean isPlayer){
        int index = 404;

        for (int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                if(entity.hitBox.intersects(gp.obj[i].hitBox)){
                    System.out.println("hit " + gp.obj[i].name);
                    return i;
                }
            }
        }

        return index;
    }



    public int checkEntity(Entity entity, Entity[] entities){
        int index = 404;

        for (int i = 0; i < entities.length; i++){
            if(entities[i] != null){
                if(entity.hitBox.intersects(entities[i].hitBox)){
                    System.out.println("hit ");
                    return i;
                }
            }
        }

        return index;
    }
}
