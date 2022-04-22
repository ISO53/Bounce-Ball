/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bounceball;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author termi
 */
public class Slab extends JPanel{
    
    Color color;
    
    public Slab(Color selectedColor) {
        super();
        this.setOpaque(false);
        this.color = selectedColor;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        g.setColor(this.color);
        g.fillRoundRect(0, 0, 25, 100, 25, 25);
    }
}
