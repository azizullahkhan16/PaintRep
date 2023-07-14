package uicomponents.MyToolbar;

import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonToggle;
import uicomponents.MyLinkedList.MyLinkedList;
import uicomponents.MyLinkedList.MyUndoStack;
import uicomponents.MyShapes.*;
import uicomponents.MyShapes.Rectangle;
import uicomponents.MyShapes.MyShape;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

/**
 * This class creates drawing board
 */

public class MyDrawingBoard implements PortionListener {

    private int x, y, width, height;

    private int x1, y1, x2, y2;

    private MyShape tempShape;
    private boolean pressed = false;
    private Dots pointCircle;
    private int numCount = 0;
    private int bx0, by0, bx1, by1, bx2, by2, bx3, by3;
    private boolean showLinearBezierCurve = false;
    private boolean showQuadraticBezierCurve = false;
    private boolean showCubicBezierCurve = false;
    private MyCurves myCurves;
    private ArrayList<MyLinkedList.Node> layers;

    private MyLayerBar myLayerBar;


    public MyDrawingBoard(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        pointCircle = new Dots();
        myLayerBar = new MyLayerBar();
    }

    public void paint(Graphics g, ImageObserver observer){
        if (pressed && tempShape != null) tempShape.paint(g);
        if(pointCircle != null) pointCircle.paint(g);

        layers = MyLayerBar.getLayersButton().getLayersReverse();


        for (int i = layers.size()-1; i >= 0; i--) {
            MyUndoStack.MyNode myNode = layers.get(i).buttonLayer.getMyUndoStack().head;
            while (myNode != null) {
                MyShape s = myNode.shape;
                if (s instanceof MyCurves) ((MyCurves) s).drawCubicBezierCurve(g);
                else s.paint(g);
                myNode = myNode.next;
            }
        }

        if(showLinearBezierCurve && myCurves != null) myCurves.drawLinearBezierCurve(g);
        else if(showQuadraticBezierCurve && myCurves != null) myCurves.drawQuadraticBezierCurve(g);
        else if(showCubicBezierCurve && myCurves != null) myCurves.drawCubicBezierCurve(g);
    }

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }


    @Override
    public void onClick(int x, int y) {


    }

    public void onDrag(int x, int y){

        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            x2 = x;
            y2 = y;

            for (ButtonToggle s : MyShapeBar.getShapes()) {
                if (s.getPressed()) {
                    if (s.getType().equals("Circle"))
                        tempShape = new Circle(x1, y1, x2, y2, MyColorBar.getFillColor(), MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    else if (s.getType().equals("Rectangle"))
                        tempShape = new Rectangle(x1, y1, x2, y2, MyColorBar.getFillColor(), MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    else if (s.getType().equals("Right Triangle"))
                        tempShape = new Triangle(x1, y1, x2, y2, MyColorBar.getFillColor(), MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    else if (s.getType().equals("Equilateral Triangle"))
                        tempShape = new EquiTriangle(x1, y1, x2, y2, MyColorBar.getFillColor(), MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    else if (s.getType().equals("Pentagram"))
                        tempShape = new Pentagram(x1, y1, x2, y2, MyColorBar.getFillColor(), MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    else if (s.getType().equals("Hexagon"))
                        tempShape = new Hexagon(x1, y1, x2, y2, MyColorBar.getFillColor(), MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                }
                pressed = true;
            }

            if (MyFreeDrawing.drawingPressed()) {
                pointCircle.getPointCircles().add(new Dots(MyStrokeWidth.getStrokeWidth(), new Point(x, y), MyColorBar.getStrokeColor()));
            }

            if (MyCurves.curvePressed()) {
                if (numCount == 1) {
                    bx1 = x;
                    by1 = y;
                    myCurves = new MyCurves(bx0, by0, bx1, by1, bx2, by2, bx3, by3, MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    myCurves.drawLinearBezierCurve();
                    System.out.println("The " + numCount + " point is (" + bx1 + ", " + by1 + ")");
                    showLinearBezierCurve = true;
                } else showLinearBezierCurve = false;

                if (numCount == 2) {
                    bx2 = x;
                    by2 = y;
                    myCurves = new MyCurves(bx0, by0, bx1, by1, bx2, by2, bx3, by3, MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    myCurves.drawQuadraticBezierCurve();
                    System.out.println("The " + numCount + " point is (" + bx2 + ", " + by2 + ")");
                    showQuadraticBezierCurve = true;
                } else showQuadraticBezierCurve = false;

                if (numCount == 3) {
                    bx3 = x;
                    by3 = y;
                    myCurves = new MyCurves(bx0, by0, bx1, by1, bx2, by2, bx3, by3, MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                    myCurves.drawCubicBezierCurve();
                    System.out.println("The " + numCount + " point is (" + bx3 + ", " + by3 + ")");
                    showCubicBezierCurve = true;
                } else showCubicBezierCurve = false;
            }

        }

    }

    @Override
    public void onPress(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            x1 = x;
            y1 = y;


            if(MyCurves.curvePressed()) {
                if (numCount == 0) {
                    bx0 = x;
                    by0 = y;
                    System.out.println("The " + numCount + " point is (" + bx0 + ", " + by0 + ")");
                    numCount++;
                } else if (numCount == 2) {
                    bx2 = x;
                    by2 = y;
                    System.out.println("The " + numCount + " point is (" + bx2 + ", " + by2 + ")");
                } else if (numCount == 3) {
                    bx3 = x;
                    by3 = y;
                    System.out.println("The " + numCount + " point is (" + bx3 + ", " + by3 + ")");
                }
            }


            if(MyFreeDrawing.drawingPressed()){
                pointCircle = new Dots();
            }
        }

    }

    @Override
    public void onRelease(int x, int y) {



        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            if (tempShape != null) {
                MyLayerBar.getCurrentButton().getMyRedoStack().clear();
                MyLayerBar.getCurrentButton().getMyUndoStack().push(tempShape);
                tempShape = null;
            }

            if (MyFreeDrawing.drawingPressed()) {
                if (!pointCircle.getPointCircles().isEmpty()) {
                    if(!MyLayerBar.getCurrentButton().getMyRedoStack().isEmpty()) MyLayerBar.getCurrentButton().getMyRedoStack().clear();
                    MyLayerBar.getCurrentButton().getMyUndoStack().push(pointCircle);
                    pointCircle = null;
                }
            }


            if (numCount == 1) {
                bx1 = x;
                by1 = y;
                myCurves = new MyCurves(bx0, by0, bx1, by1, bx2, by2, bx3, by3, MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                myCurves.drawLinearBezierCurve();
                System.out.println("The " + numCount + " point is (" + bx1 + ", " + by1 + ")");
                numCount++;
            } else if (numCount == 2) {
                bx2 = x;
                by2 = y;
                myCurves = new MyCurves(bx0, by0, bx1, by1, bx2, by2, bx3, by3, MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                myCurves.drawQuadraticBezierCurve();
                System.out.println("The " + numCount + " point is (" + bx2 + ", " + by2 + ")");
                numCount++;
            } else if (numCount == 3) {
                bx3 = x;
                by3 = y;
                myCurves = new MyCurves(bx0, by0, bx1, by1, bx2, by2, bx3, by3, MyColorBar.getStrokeColor(), MyStrokeWidth.getStrokeWidth());
                myCurves.drawCubicBezierCurve();
                System.out.println("The " + numCount + " point is (" + bx3 + ", " + by3 + ")");
                if(!MyLayerBar.getCurrentButton().getMyRedoStack().isEmpty()) MyLayerBar.getCurrentButton().getMyRedoStack().clear();
                MyLayerBar.getCurrentButton().getMyUndoStack().push(myCurves);
                myCurves = null;
                numCount = 0;
            }

        }



    }

    @Override
    public void onMove(int x, int y) {

    }
}
