package uicomponents.MyWindow;

import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonActive;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This is the abstract class to create window
 */


public class MyWindow{


    public int x, y, width, height;

    public MyWindow(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public MyWindow(){}

    public void paint(Graphics g, ImageObserver observer){
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y+height/9 , width, height-height/9);

    }
}
