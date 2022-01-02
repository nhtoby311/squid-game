package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Entity{
    int movementTimeout = 0;

    public Enemy(GamePanel gp){
        super(gp);



        getImage();
        setDefaultValues();
    }

    public void setDefaultValues(){

        x = 14 * gp.tileSize;
        y = 8 * gp.tileSize;
        speed = 2;
        direction = "down";


        hitBox = new Rectangle();
        hitBox.x = x + 8;
        hitBox.y = y + 16;
        hitBox.width = 32;
        hitBox.height = 32;
    }


    public void getImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/enemy/worker_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setMovement(){
        int min = 1;
        int max = 4;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        switch (randomNum){
            case 1:
                direction = "up";
                break;
            case 2:
                direction = "down";
                break;
            case 3:
                direction = "left";
                break;
            case 4:
                direction = "right";
                break;
        }
    }

    public void update(){
        if (gp.rlgl.redLight){
            movementTimeout++;
            if (movementTimeout > 60*1){
                setMovement();
                movementTimeout = 0;
            }


            collisionable = false;
            gp.colliCheck.checkTile(this);
            //pickObject(indexObj);
            if (this.hitBox.intersects(gp.player.hitBox)) gp.gameOver = true; //check if hit player

            //if collistion, cant move
            if (collisionable == false){
                switch (direction){
                    case "up":
                        y -= speed;
                        hitBox.y = y + 16;
                        //System.out.println("y: " + hitBox.y);
                        break;
                    case "down":
                        y += speed;
                        hitBox.y = y + 16;
                        break;
                    case "left":
                        x -= speed;
                        hitBox.x = x + 8;
                        break;
                    case "right":
                        x += speed;
                        hitBox.x = x + 8;
                        break;
                }
            }
            else{
                setMovement();                  //Advance AI
            }

            spriteCnt++;
            if (spriteCnt > 10){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCnt = 0;
            }
        }


    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                else if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                else if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize, gp.tileSize, null);

        //g2.draw(hitBox);
    }
}
