package uicomponents.MyShapes;

import java.awt.*;

public class Triangle extends MyShape {

    private Point bottomLeft;

    public Triangle(int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, int strokeWidth) {
        super(x1, y1, x2, y2, fillColor, strokeColor, strokeWidth);
        bottomLeft = new Point(x1, y2);
    }

    @Override
    public void paint(Graphics g) {
        if (g == null) return;

        Graphics2D graphics2D = (Graphics2D) g;

        g.setColor(fillColor);
        g.fillPolygon(new int[]{top.x, bottomLeft.x, bottomRight.x}, new int[]{top.y, bottomLeft.y, bottomRight.y}, 3);

        g.setColor(strokeColor);
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        g.drawPolygon(new int[]{top.x, bottomLeft.x, bottomRight.x}, new int[]{top.y, bottomLeft.y, bottomRight.y}, 3);
    }
}
