package uicomponents.MyToolbar;

import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonToggle;
import uicomponents.MyToolTip.MyToolTip;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class MyFreeDrawing extends MyRectangle implements PortionListener {

    private static ButtonToggle drawing;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;

    public MyFreeDrawing(int x, int y, int width, int height) {
        super(x, y, width, height);
        drawing = new ButtonToggle(x+14, y+22, 64, 86, false, new ImageIcon("pencil.png").getImage(), "freeDraw");

    }

    public static boolean drawingPressed(){
        return drawing.getPressed();
    }

    public void paint(Graphics g, ImageObserver observer){
        super.paint(g);
        drawing.paint(g, observer);

        Font font = new Font("Arial", Font.PLAIN, 14);
        g.setFont(font);
        g.setColor(Color.BLACK);

        g.drawString("Drawing", x+20, 138);

        if(showToolTip) myToolTip.paint(g);

    }

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    public static void setDrawingUnpressed(int x ,int y){
        drawing.setPressed(false);
    }

    @Override
    public void onClick(int x, int y) {
        if(drawing.IsClicked(x, y)){
            MyShapeBar.setShapesUnpressed(x, y);
            MyCurves.setCurveUnpressed(x, y);
            drawing.setPressed(true);
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
        if(drawing.isPressed(x, y)){
            myToolTip = MyToolTip.getInstance(x, y, "Free Drawing");
            showToolTip = true;
        }
    }
}
