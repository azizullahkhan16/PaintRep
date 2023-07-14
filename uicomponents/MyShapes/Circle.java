package uicomponents.MyShapes;

import java.awt.*;

public class Circle extends MyShape {

    private int diameter;
    private Point center;

    public Circle(int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, int strokeWidth) {
        super(x1, y1, x2, y2, fillColor, strokeColor, strokeWidth);
        this.center = new Point(x1, y1);
        calculateDiameter();
    }


    private void setDiameter(int diameter) {
        if (diameter > 1) this.diameter = diameter;
        else this.diameter = 1;

    }

    public void calculateDiameter() {
        int width = bottomRight.x - top.x;
        int height = bottomRight.y - top.y;
        diameter = Math.min(width, height);
    }


    public void paint(Graphics g) {
        g.setColor(strokeColor); // Setting the color
        g.fillOval(center.x,center.y, diameter, diameter);
        g.setColor(fillColor);
        g.fillOval(center.x+strokeWidth,center.y+strokeWidth, diameter-2*strokeWidth , diameter-2*strokeWidth);

    }
}
