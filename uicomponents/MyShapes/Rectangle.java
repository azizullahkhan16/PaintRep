package uicomponents.MyShapes;

import java.awt.*;

public class Rectangle extends MyShape {


    public Rectangle(int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, int strokeWidth) {
        super(x1, y1, x2, y2, fillColor, strokeColor, strokeWidth);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(strokeColor);

        int width = bottomRight.x - top.x;
        int height = bottomRight.y - top.y;

        if(width < 0 && height < 0) {
            g.fillRect(bottomRight.x, bottomRight.y, Math.abs(width)+2*strokeWidth, Math.abs(height)+2*strokeWidth);
            g.setColor(fillColor);
            g.fillRect(bottomRight.x+strokeWidth, bottomRight.y+strokeWidth, Math.abs(width), Math.abs(height));
        } else if(width < 0 && height > 0) {
            g.fillRect(bottomRight.x, top.y, Math.abs(width)+2*strokeWidth, Math.abs(height)+2*strokeWidth);
            g.setColor(fillColor);
            g.fillRect(bottomRight.x+strokeWidth, top.y+strokeWidth, Math.abs(width), Math.abs(height));
        } else if(width > 0 && height < 0) {
            g.fillRect(top.x, bottomRight.y, Math.abs(width)+2*strokeWidth, Math.abs(height)+2*strokeWidth);
            g.setColor(fillColor);
            g.fillRect(top.x+strokeWidth, bottomRight.y+strokeWidth, Math.abs(width), Math.abs(height));
        } else {
            g.fillRect(top.x, top.y, width+2*strokeWidth, height+2*strokeWidth);
            g.setColor(fillColor);
            g.fillRect(top.x+strokeWidth, top.y+strokeWidth, width, height);

        }

    }
}
