package uicomponents.MyShapes;

import java.awt.*;
import java.io.Serializable;

public abstract class MyShape implements Serializable {
    protected Point top;
    protected Point bottomRight;
    protected Color fillColor;
    protected Color strokeColor;
    protected int strokeWidth;

    public MyShape(int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, int strokeWidth) {
        top = new Point(x1, y1);
        bottomRight = new Point(x2, y2);
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    public MyShape(){}

    public abstract void paint(Graphics g);
}
