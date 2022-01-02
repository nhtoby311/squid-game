package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class RedLightGreenLightSim {

    GamePanel gp;

    public boolean redLight = true;
    public int greenLightDuration = 0;
    public int redLightDuration = 2;
    public int timeRemain;

    public BufferedImage image;
    public BufferedImage image2;



    public RedLightGreenLightSim(GamePanel gp){
        this.gp = gp;
        timeRemain = (int)gp.timer - redLightDuration;


        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/exit.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/exit1.png"));

        }catch(
                IOException e){

        }
    }


    public void simulate(){

        if (gp.timer < timeRemain && redLight == true){
            redLight = false;
            int min = 1;
            int max = 5;
            greenLightDuration = ThreadLocalRandom.current().nextInt(min, max + 1);
            System.out.println("greenLightDuration:" + greenLightDuration);
            timeRemain = (int)gp.timer - greenLightDuration;
            System.out.println("timeRemain" + timeRemain);
            gp.obj[1].image = image2;
        }
        else if (gp.timer < timeRemain && redLight == false){
            System.out.println("Red light now");
            redLight = true;
            timeRemain = (int)gp.timer - redLightDuration;
            gp.obj[1].image = image;
        }

    }
}
