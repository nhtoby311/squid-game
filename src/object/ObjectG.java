package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectG {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x,y;
    public Rectangle hitBox = new Rectangle(0,0,48,48);


    public void draw(Graphics2D g2, GamePanel gp){

        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
        //g2.draw(hitBox);
    }
}
