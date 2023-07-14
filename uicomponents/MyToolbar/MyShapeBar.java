package uicomponents.MyToolbar;

import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonToggle;
import uicomponents.MyToolTip.MyToolTip;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

/**
 * This class creates shape bar
 */


public class MyShapeBar extends MyRectangle implements PortionListener {

    private static ArrayList<ButtonToggle> shapes;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;

    private int buttonSize = 30;

    public MyShapeBar(int x, int y, int width, int height) {
        super(x, y, width, height);

        shapes = new ArrayList<>();

        shapes.add(new ButtonToggle(x+(width*1)/7, y+16, buttonSize, buttonSize, false, new ImageIcon("circle.png").getImage(), "Circle"));
        shapes.add(new ButtonToggle(x+(width*1)/7 + (1 * buttonSize)+16, y+16, buttonSize, buttonSize, false, new ImageIcon("rectangle.png").getImage(), "Rectangle"));
        shapes.add(new ButtonToggle(x+(width*1)/7 + (2 * buttonSize)+32, y+16, buttonSize, buttonSize, false, new ImageIcon("rightTriangle.png").getImage(), "Right Triangle"));
        shapes.add(new ButtonToggle(x+(width*1)/7, y+32+buttonSize, buttonSize, buttonSize, false, new ImageIcon("equTriangle.png").getImage(), "Equilateral Triangle"));
        shapes.add(new ButtonToggle(x+(width*1)/7 + (1 * buttonSize)+16, y+32+buttonSize, buttonSize, buttonSize, false, new ImageIcon("pentagon.png").getImage(), "Pentagram"));
        shapes.add(new ButtonToggle(x+(width*1)/7 + (2 * buttonSize)+32, y+32+buttonSize, buttonSize, buttonSize, false, new ImageIcon("hexagon.png").getImage(), "Hexagon"));


   }


    public void paint(Graphics g, ImageObserver observer){
        super.paint(g);

        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawString("Shapes", x+(width*1)/6+36, 150);

        for (ButtonToggle b : shapes){
            b.paint(g, observer);
        }


        if(showToolTip) myToolTip.paint(g);

    }

    public static void setShapesUnpressed(int x, int y){
        for (ButtonToggle b1 : shapes){
            b1.setPressed(false);
        }
    }

    public static ArrayList<ButtonToggle> getShapes() {
        return shapes;
    }

    @Override
    public void onClick(int x, int y) {
        for (ButtonToggle b : shapes){
            if(b.IsClicked(x, y)){
                for (ButtonToggle b1 : shapes){
                    b1.setPressed(false);
                }
                b.setPressed(true);
                MyFreeDrawing.setDrawingUnpressed(x, y);
                MyCurves.setCurveUnpressed(x, y);
                return;
            }
        }
    }

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    @Override
    public void onPress(int x, int y) {

    }

    @Override
    public void onRelease(int x, int y) {

    }

    @Override
    public void onMove(int x, int y) {
        showToolTip = false;
        for (ButtonToggle b : shapes){
            if(b.isPressed(x, y)){
               myToolTip = MyToolTip.getInstance(x, y, b.getType());
               showToolTip = true;
            }
        }

    }
}
