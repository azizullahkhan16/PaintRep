package uicomponents.MyShapes;

import java.awt.*;

public class Hexagon extends MyShape {

    private int[] xPoints;
    private int[] yPoints;

    public Hexagon(int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, int strokeWidth) {
        super(x1, y1, x2, y2, fillColor, strokeColor, strokeWidth);
        calculatePoints();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, 6);

        g.setColor(strokeColor);
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        g.drawPolygon(xPoints, yPoints, 6);
    }

    public void calculatePoints() {
        // Calculate the height of the hexagon
        double height = bottomRight.getY() - top.getY();

        // Calculate the side length of the hexagon
        double sideLength = height / 2 / Math.sin(Math.toRadians(60));

        // Calculate the x-coordinate of the center of the hexagon
        double centerX = (top.getX() + bottomRight.getX()) / 2;

        // Calculate the y-coordinate of the center of the hexagon
        double centerY = top.getY() + height / 2;

        // Create an array to hold the six points of the hexagon
        xPoints = new int[6];
        yPoints = new int[6];

        // Calculate the coordinates of each point and add it to the array
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            double x = centerX + sideLength * Math.cos(angle);
            double y = centerY + sideLength * Math.sin(angle);
            xPoints[i] = (int) x;
            yPoints[i] = (int) y;
        }

    }

}

