package uicomponents.MyToolbar;

import uicomponents.MyShapes.MyShape;

import java.awt.*;

/**
 * This is the abstract class to create rectangle
 */

public class MyRectangle extends MyShape {

    int x;
    int y;
    int width;
    int height;

    public MyRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public MyRectangle(){}


    public void paint(Graphics g){
        g.setColor(Color.black);

        g.fillRect(x, y, width, height);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x+2, y, width-4, height-2);
    }
}
