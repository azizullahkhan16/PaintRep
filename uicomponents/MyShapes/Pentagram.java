package uicomponents.MyShapes;

import java.awt.*;

public class Pentagram extends MyShape {
    private int[] xPoints;
    private int[] yPoints;

    public Pentagram(int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, int strokeWidth) {
        super(x1, y1, x2, y2, fillColor, strokeColor, strokeWidth);
        calculatePoints();
    }

    private void calculatePoints() {
        int x = (top.x + bottomRight.x) / 2;
        int y = (top.y + bottomRight.y) / 2;

        int r = (int) Math.sqrt(Math.pow(x - top.x, 2) + Math.pow(y - bottomRight.y, 2));

        xPoints = new int[10];
        yPoints = new int[10];
        double angle = Math.PI / 2;

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                xPoints[i] = (int) (x + r * Math.cos(angle));
                yPoints[i] = (int) (y - r * Math.sin(angle));
            } else {
                xPoints[i] = (int) (x + r / 2 * Math.cos(angle));
                yPoints[i] = (int) (y - r / 2 * Math.sin(angle));

            }
            angle += Math.PI / 5;
        }

    }

    public void paint(Graphics g) {

        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, 10);
        Graphics2D graphics2D = (Graphics2D) g;
        g.setColor(strokeColor);
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        g.drawPolygon(xPoints, yPoints, 10);
    }
}



