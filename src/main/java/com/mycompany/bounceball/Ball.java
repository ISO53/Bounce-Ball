
package com.mycompany.bounceball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ISO53
 */
public class Ball extends JPanel implements Runnable {

    int direction;
    // DIRECTION   0 = northwest  1 = northeast  2 = southeast  3 = southwest

    private double delay;
    Thread ballThread;
    boolean isRunning;
    private int hitCounter;

    public Ball() {
        super();
        this.setOpaque(false);
        direction = 0;
        delay = 5;
        ballThread = new Thread(this);
        isRunning = true;
        hitCounter = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, 24, 24);
    }

    public void start() {
        direction = 3;
        delay = 5;
        hitCounter = 0;
        ballThread.start();
    }

    public void moveTheBall(int direction) {

        switch (direction) {
            case 0: // Northwest
                this.setLocation(this.getLocation().x - 1, this.getLocation().y - 1);
                break;

            case 1: // Northeast
                this.setLocation(this.getLocation().x + 1, this.getLocation().y - 1);
                break;

            case 2: // Southeast
                this.setLocation(this.getLocation().x + 1, this.getLocation().y + 1);
                break;

            case 3: // Southwest
                this.setLocation(this.getLocation().x - 1, this.getLocation().y + 1);
                break;
        }
    }

    public void collisionHappens() {
        //************************ LEFT SLAB COLLISION ************************
        if (this.getLocation().x == 50) {
            if (this.getParent().getComponent(0).getY() < this.getY() + 24) {
                if (this.getParent().getComponent(0).getY() + 100 > this.getY() + 24) {
                    // 1 OR 2
                    if (direction == 0) {
                        direction = 1;
                    } else {
                        direction = 2;
                    }

                    setHitCounter(getHitCounter() + 1);

                    BounceBall.lbl_score.setText("SCORE: " + getHitCounter());
                    BounceBall.lbl_speed.setText("SPEED: " + 100 / getDelay());
                    
                } else {
                    BounceBall.lbl_game.setForeground(Color.RED);
                    BounceBall.lbl_game.setText("G A M E  O V E R");
                    isRunning = false;
                }
            } else {
                BounceBall.lbl_game.setForeground(Color.RED);
                BounceBall.lbl_game.setText("G A M E  O V E R");
                isRunning = false;
            }
        }
        //*********************************************************************

        //************************ RIGHT SLAB COLLISION ************************
        if (this.getLocation().x == 912 - 24) {
            if (this.getParent().getComponent(1).getY() < this.getY() + 24) {
                if (this.getParent().getComponent(1).getY() + 100 > this.getY() + 24) {
                    // 0 OR 3
                    if (direction == 1) {
                        direction = 0;
                    } else {
                        direction = 3;
                    }

                    setHitCounter(getHitCounter() + 1);

                    BounceBall.lbl_score.setText("SCORE: " + getHitCounter());
                    BounceBall.lbl_speed.setText("SPEED: " + 100 / getDelay());
                    
                } else {
                    BounceBall.lbl_game.setForeground(Color.RED);
                    BounceBall.lbl_game.setText("G A M E  O V E R");
                    isRunning = false;
                }
            } else {
                BounceBall.lbl_game.setForeground(Color.RED);
                BounceBall.lbl_game.setText("G A M E  O V E R");
                isRunning = false;
            }
        }
        //*********************************************************************

        //************************ TOP WALL COLLISION ************************
        if (this.getLocation().y == 0) {
            // 2 OR 3
            if (direction == 0) {
                direction = 3;
            } else {
                direction = 2;
            }
        }
        //*********************************************************************

        //************************ BOTTOM WALL COLLISION ************************
        if (this.getLocation().y == 445 - 24) {
            // 0 OR 1
            if (direction == 2) {
                direction = 1;
            } else {
                direction = 0;
            }
        }
        //*********************************************************************
    }

    @Override
    public void run() {
        while (isRunning) {
            moveTheBall(direction);

            try {
                ballThread.sleep((long) getDelay());
            } catch (InterruptedException ex) {
                Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
            }

            collisionHappens();

            this.getParent().getComponent(0).setLocation(this.getParent().getComponent(0).getLocation().x, this.getLocation().y - 50);
        }
    }
    
    public double getDelay() {
        return delay;
    }
    
    public void setDelay(double delay) {
        this.delay = delay;
    }
    
    public int getHitCounter() {
        return hitCounter;
    }
    
    public void setHitCounter(int hitCounter) {
        this.hitCounter = hitCounter;
    }
    
}
