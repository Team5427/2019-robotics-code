package org.usfirst.frc.team5427;

import java.awt.*;
import javax.swing.*;

public class AutoPanel extends JPanel  {
    public AutoPanel(int w,int h) {
        super();
        setSize(w,h);
    }

    public void paint(Graphics g) {
        //Background
        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());
      
    }
}