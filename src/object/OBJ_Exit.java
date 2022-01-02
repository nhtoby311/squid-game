package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Exit extends ObjectG {
    public BufferedImage image2;

    public OBJ_Exit(){
        name="Exit";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/exit.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/money.png"));

        }catch(IOException e){

        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp){

        g2.drawImage(image,x,y,gp.tileSize,26*3,null);
        //g2.draw(hitBox);
    }
}
