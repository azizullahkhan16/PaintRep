package uicomponents.MyShapes;

import java.awt.*;
import java.util.ArrayList;

public class Dots extends MyShape {


    private int diameter;
    private Point centre;

    private Color fillColor;
    private ArrayList<Dots> pointCircles;

    public Dots(int diameter, Point centre, Color fillColor) {
        this.diameter = 3*diameter;
        this.centre = centre;
        this.fillColor = fillColor;

    }

    public Dots(){
        pointCircles = new ArrayList<>();
    }

    public ArrayList<Dots> getPointCircles() {
        return pointCircles;
    }

    public void paint(Graphics g){

        for (Dots d : pointCircles) {
            g.setColor(d.fillColor);
            g.fillOval(d.centre.x, d.centre.y, d.diameter, d.diameter);
        }
    }
}
