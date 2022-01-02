package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    public int money = 0;

    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);

        this.keyH = keyH;


        hitBox = new Rectangle();
        hitBox.x = 100 + 8;
        hitBox.y = 100 + 16;
        hitBox.width = 32;
        hitBox.height = 32;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(gp.rlgl.redLight){
                gp.gameOver = true;
                money = 0;
            }

            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if (keyH.leftPressed == true){
                direction = "left";
            }
            else if (keyH.rightPressed == true){
                direction = "right";
            }

            collisionable = false;
            gp.colliCheck.checkTile(this);
            int indexObj = gp.colliCheck.checkObject(this,true);
            pickObject(indexObj);

            int indexEnemy = gp.colliCheck.checkEntity(this,gp.enemies);
            hitByEnemy(indexEnemy);

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

    public void pickObject(int i){
        if (i != 404){
            String objName = gp.obj[i].name;

            switch(objName){
                case "Money":
                    gp.obj[i] = null;
                    money+=1000;
                    //System.out.println(money);
                    gp.as.setRandomMoney(i);
                    break;
                case "Exit":
                    gp.gameOver = true;
                    System.out.println("yeet");
                    break;
                case "Boots":
                    gp.obj[i] = null;
                    speed += 2;
                    break;
            }
        }

    }


    public void hitByEnemy(int i){
        if (i != 404){
            money = 0;
            gp.gameOver = true;
        }

    }


    public void draw(Graphics2D g2){
        /*g2.setColor(Color.WHITE);

        g2.fillRect(x,y,gp.tileSize,gp.tileSize);*/

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
