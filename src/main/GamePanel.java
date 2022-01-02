package main;

import entity.Enemy;
import entity.Entity;
import entity.Player;
import object.ObjectG;
import tile.TileManager;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements Runnable{

    int originalTileSize = 16;
    int scale = 3;

    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 28;
    public int maxScreenRow = 16;
    public int screenWidth = tileSize * maxScreenCol;
    public int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;


    public boolean gameOver = false;

    public double timer = 120;

    public double spawnTime = timer - 3;
    public int enemyCounter = 1;

    Thread gameThread;
    KeyHandler keyInp = new KeyHandler();
    public Player player = new Player(this,keyInp);
    TileManager tileM = new TileManager(this);
    public CollisionChecker colliCheck = new CollisionChecker(this);
    public AssetSetter as = new AssetSetter(this);
    public ObjectG obj[] = new ObjectG[10];
    public UI ui = new UI(this);
    public Enemy enemies[] = new Enemy[20];
    public RedLightGreenLightSim rlgl = new RedLightGreenLightSim(this);



    public void setup(){
        as.setInitObject();
    }


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInp);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long curTime;
        long timer = 0;
        int fpsCnt = 0;

        while (gameThread != null){

            curTime = System.nanoTime();

            delta += (curTime - lastTime) / drawInterval;
            timer += (curTime - lastTime);
            lastTime = curTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
                fpsCnt++;
            }

            if(timer >= 1000000000){
                //System.out.println("FPS: " + fpsCnt);
                fpsCnt = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if (!gameOver){
            if (timer >= 0){
                //System.out.println(timer);
                timer-= (double)1/60;

                rlgl.simulate();
                player.update();

                as.spawningEnemy();

                for(int i = 0; i < enemies.length; i++){
                    if(enemies[i] != null){
                        enemies[i].update();
                    }
                }
            }

            else gameOver = true;


        }

    }

    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2 = (Graphics2D)g;



        tileM.draw(g2);

        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2,this);
            }
        }


        for (int i = 0; i < enemies.length;i++){
            if(enemies[i] != null){
                enemies[i].draw(g2);
            }
        }

        player.draw(g2);

        ui.draw(g2);


        g2.dispose();

    }

}
