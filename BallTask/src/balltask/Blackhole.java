/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balltask;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author dam
 */
public class Blackhole{
    
    int width;
    int height;    
    int x;
    int y;

    Boolean free;
    
    public Blackhole(int x, int y, int width, int height)
    {
        this.width = width;
        this.height = height;
        
        this.x = x;
        this.y = y;

        free = true;
    }
    
    public void drawRectangle(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(x, y, width, height);
    }
    
    
    public synchronized void in(Ball bola) {
        while (!free) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        free = false;
    }

    public synchronized void out() {
            free = true;
            notifyAll();
    }
}
