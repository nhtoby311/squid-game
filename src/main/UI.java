package main;
import object.OBJ_Money;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    BufferedImage moneyImg;
    DecimalFormat dFormat = new DecimalFormat("#0");


    public UI(GamePanel gp){
        this.gp = gp;
        OBJ_Money m = new OBJ_Money();
        moneyImg = m.image;
    }
    public void draw(Graphics2D g2){
        if (gp.gameOver){
            g2.setFont(new Font("Arial", Font.BOLD,45));
            g2.setColor(Color.ORANGE);
            String text = "Game over! You leave with " + gp.player.money + "$";
            int textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

            g2.drawString(text,gp.screenWidth/2 - textLength/2,gp.screenHeight/2 -(gp.tileSize *3));

        }
        else{
            g2.setFont(new Font("Arial", Font.PLAIN,40));
            g2.setColor(Color.WHITE);
            g2.drawImage(moneyImg,gp.tileSize/2, gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString(": "+ gp.player.money,80,70);
            g2.drawString(": "+ gp.player.money +"$",80,70);

            g2.drawString("Time:" + dFormat.format(gp.timer),gp.tileSize*23,70);
        }

    }
}