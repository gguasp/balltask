/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author dam
 */
public class Viewer extends Canvas implements Runnable {



    private BufferedImage background;

    public Viewer() {
        try {
            background = ImageIO.read(new File("background.jpg"));
        } catch (IOException e) {
        }

        for (int i = 0; i < BallTask.ballArray.length; i++) {
            Ball ball = BallTask.ballArray[i];
            BallTask.ballArray[i] = new Ball(30, 30);
            (new Thread(BallTask.ballArray[i])).start();
        }
        
        BallTask.bhArray[0] = new Blackhole(10, 25, 200, 50);
        BallTask.bhArray[1] = new Blackhole(300, 100, 80, 150);

        Statistics stats = new Statistics();
        (new Thread(stats)).start();
        
    }

    public void run() {
        this.createBufferStrategy(2);

        while (true) {
            this.paint();
            try {
                Thread.sleep(5); // 
            } catch (InterruptedException ex) {
            }
        }
    }

    public void paint() {

        BufferStrategy bs;
        bs = this.getBufferStrategy();

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.drawImage(this.background, 0, 0, 512, 512, null);

        BallTask.bhArray[0].drawRectangle(g);
        BallTask.bhArray[1].drawRectangle(g);

        for (int i = 0; i < BallTask.ballArray.length; i++) {
            BallTask.ballArray[i].drawBall(g);
        }
        
        g.setColor(Color.WHITE);
        g.drawString("Bolas: "+Statistics.totalBalls, 20, 20);
        g.drawString("En espera: "+Statistics.onWait, 20, 40);
        g.drawString("En agujero: "+Statistics.onBh, 20, 60);
        
        System.out.flush();

        bs.show();
        g.dispose();

    }

}
