package org.usfirst.frc.team5427;

import javax.swing.*;

public class AutoPath extends JFrame {
    AutoPanel p =null;
    
    public AutoPath(String title) {
        super(title);
        setSize(750,535);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        p=new AutoPanel(150,70);
        p.setBounds(590,410,150,70);
        add(p);
        
        setVisible(true);
    }

}
