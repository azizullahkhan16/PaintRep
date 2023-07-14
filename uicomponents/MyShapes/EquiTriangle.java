package uicomponents.MyShapes;

import java.awt.*;


public class EquiTriangle extends MyShape {

    private int[] xPoints;
    private int[] yPoints;

    public EquiTriangle(int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, int strokeWidth) {
        super(x1, y1, x2, y2, fillColor, strokeColor, strokeWidth);
        calculatePoints();
    }

    public void calculatePoints() {
        // Calculate the length of one side of the equilateral triangle
        double sideLength = (bottomRight.getY() - top.getY()) * Math.sqrt(3) / 3;

        // Calculate the coordinates of the bottom left point
        double bottomLeftX = bottomRight.getX() - 2 * sideLength;
        double bottomLeftY = bottomRight.getY();

        // Calculate the coordinates of the top of the equilateral triangle
        double topX = (bottomRight.getX() + bottomLeftX) / 2;
        double topY = top.getY();

        // Create an array to hold the three points of the equilateral triangle
        xPoints = new int[3];
        yPoints = new int[3];

        // Assign the coordinates of the three points to the array
        xPoints[0] = (int) topX;
        xPoints[1] = (int) bottomLeftX;
        xPoints[2] = bottomRight.x;

        yPoints[0] = (int) topY;
        yPoints[1] = (int) bottomLeftY;
        yPoints[2] = bottomRight.y;
    }




    @Override
    public void paint(Graphics g) {
        g.setColor(fillColor);
        g.fillPolygon(xPoints, yPoints, 3);
        Graphics2D graphics2D = (Graphics2D) g;
        g.setColor(strokeColor);
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        g.drawPolygon(xPoints, yPoints, 3);
    }
}

