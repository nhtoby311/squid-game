package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int x,y;
    public int speed;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;

    public int spriteCnt = 0;
    public int spriteNum = 1;
    public Rectangle hitBox;
    public boolean collisionable = false;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

}
