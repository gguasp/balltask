/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author dam
 */
public class Ball implements Runnable {

    int width;
    int height;
    int x;
    int y;

    int xspeed;
    int yspeed;

    Boolean enBlackhole;
    Boolean enEspera;
    Blackhole currentBlackhole;

    public Ball(int width, int height) {
        this.width = width;
        this.height = height;

        Random random = new Random();

        this.x = random.nextInt(BallTask.frameWidth - 30);
        this.y = random.nextInt(BallTask.frameHeight - 30);

        this.xspeed = random.nextInt(10 + 10) - 10;
        this.yspeed = random.nextInt(10 + 10) - 10;

        enBlackhole = false;
        enEspera = false;
    }

    public void drawBall(Graphics g) {
        if(this.enEspera){
            g.setColor(Color.RED);
        } else if(this.enBlackhole){
            g.setColor(Color.BLUE);
        } else{
            g.setColor(Color.GREEN);
        }
        
        g.fillOval(x, y, width, height);
    }

    public Boolean getEnBlackhole() {
        return enBlackhole;
    }

    public Boolean getEnEspera() {
        return enEspera;
    }

    @Override
    public void run() {

        BallTask.canTheBallMove(this);

        while (true) {

            BallTask.canTheBallMove(this);

            this.x = this.x + this.xspeed;
            this.y = this.y + this.yspeed;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }

    public void ballBounce(String str) {
        if (str == "x") {
            this.xspeed = this.xspeed * -1;
        } else if (str == "y") {
            this.yspeed = this.yspeed * -1;
        }
    }

}
