
package com.mycompany.bounceball;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author ISO53
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
