package uicomponents.MyToolbar;

import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonToggle;
import uicomponents.MyToolTip.MyToolTip;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class MyCurves extends MyRectangle implements PortionListener {

    private static ButtonToggle curve;
    private int bx0, by0, bx1, by1, bx2, by2, bx3, by3;
    private ArrayList<Point> linearLines;
    private ArrayList<Point> quadraticLines;
    private ArrayList<Point> cubicLines;
    private Color fillColor;
    private int strokeWidth;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;
    public MyCurves(int x, int y, int width, int height) {
        super(x, y, width, height);

        curve = new ButtonToggle(x+14, y+22, 64, 84, false, new ImageIcon("curve.png").getImage(), "curve");

    }

    public MyCurves(int bx0, int by0, int bx1, int by1, int bx2, int by2, int bx3, int by3, Color fillColor, int strokeWidth){
        this.bx0 = bx0;
        this.by0 = by0;

        this.bx1 = bx1;
        this.by1 = by1;

        this.bx2 = bx2;
        this.by2 = by2;

        this.bx3 = bx3;
        this.by3 = by3;

        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
        linearLines = new ArrayList<>();
        quadraticLines = new ArrayList<>();
        cubicLines = new ArrayList<>();
    }

    public void paint(Graphics g, ImageObserver observer){
        super.paint(g);
        curve.paint(g, observer);

        Font font = new Font("Arial", Font.PLAIN, 14);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Curve", x+26, 138);

        if(showToolTip) myToolTip.paint(g);

    }

    public static boolean curvePressed(){
        return curve.getPressed();
    }

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) return true;
        return false;
    }

    public void drawLinearBezierCurve() {

        double step = 0.01;
        double t = 0.0;
        int x = bx0;
        int y = by0;
        while (t < 1.0) {
            t += step;
            int x_ = (int) Math.round((1 - t) * bx0 + t * bx1);
            int y_ = (int) Math.round((1 - t) * by0 + t * by1);
            linearLines.add(new Point(x, y));
            linearLines.add(new Point(x_, y_));
            x = x_;
            y = y_;
        }
        linearLines.add(new Point(x, y));
        linearLines.add(new Point(bx1, by1));
    }

    public void drawLinearBezierCurve(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        g.setColor(fillColor);
        for (int i = 0; i < linearLines.size()-1; i++){
            g.drawLine(linearLines.get(i).x, linearLines.get(i).y, linearLines.get(i+1).x, linearLines.get(i+1).y);
        }
    }

    public void drawQuadraticBezierCurve() {

        double step = 0.01;
        double t = 0.0;
        int x = bx0;
        int y = by0;
        while (t < 1.0) {
            t += step;
            double t_ = 1.0 - t;
            int x_ = (int) Math.round(Math.pow(t_, 2) * bx0 + 2 * t_ * t * bx2 + Math.pow(t, 2) * bx1);
            int y_ = (int) Math.round(Math.pow(t_, 2) * by0 + 2 * t_ * t * by2 + Math.pow(t, 2) * by1);
            quadraticLines.add(new Point(x, y));
            quadraticLines.add(new Point(x_, y_));
            x = x_;
            y = y_;
        }
        quadraticLines.add(new Point(x, y));
        quadraticLines.add(new Point(bx1, by1));
    }
    public void drawQuadraticBezierCurve(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        g.setColor(fillColor);
        for (int i = 0; i < quadraticLines.size()-1; i++){
            g.drawLine(quadraticLines.get(i).x, quadraticLines.get(i).y, quadraticLines.get(i+1).x, quadraticLines.get(i+1).y);
        }
    }

    public void drawCubicBezierCurve() {
        double step = 0.01;
        double t = 0.0;
        int x = bx0;
        int y = by0;
        while (t < 1.0) {
            t += step;
            double t_ = 1.0 - t;
            int x_ = (int) Math.round(Math.pow(t_, 3) * bx0 + 3 * Math.pow(t_, 2) * t * bx2 + 3 * t_ * Math.pow(t, 2) * bx3 + Math.pow(t, 3) * bx1);
            int y_ = (int) Math.round(Math.pow(t_, 3) * by0 + 3 * Math.pow(t_, 2) * t * by2 + 3 * t_ * Math.pow(t, 2) * by3 + Math.pow(t, 3) * by1);
            cubicLines.add(new Point(x, y));
            cubicLines.add(new Point(x_, y_));
            x = x_;
            y = y_;
        }
        cubicLines.add(new Point(x, y));
        cubicLines.add(new Point(bx1, by1));
    }

    public void drawCubicBezierCurve(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(strokeWidth));
        g.setColor(fillColor);
        for (int i = 0; i < cubicLines.size()-1; i++){
            g.drawLine(cubicLines.get(i).x, cubicLines.get(i).y, cubicLines.get(i+1).x, cubicLines.get(i+1).y);
        }
    }

    public static void setCurveUnpressed(int x ,int y){
        curve.setPressed(false);
    }

    @Override
    public void onClick(int x, int y) {
        if(curve.IsClicked(x, y)){
            MyShapeBar.setShapesUnpressed(x, y);
            MyFreeDrawing.setDrawingUnpressed(x, y);
            curve.setPressed(true);
        }
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
        if(curve.isPressed(x, y)){
            myToolTip = MyToolTip.getInstance(x, y, "Bezier Curve");
            showToolTip = true;
        }
    }
}
