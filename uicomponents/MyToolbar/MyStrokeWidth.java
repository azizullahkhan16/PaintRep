package uicomponents.MyToolbar;

import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonActive;
import uicomponents.MyToolTip.MyToolTip;


import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class MyStrokeWidth extends MyRectangle implements PortionListener {

    private ButtonActive stroke;
    private boolean strokePress = false;
    private boolean showStrokeDropdown = false;
    private ArrayList<ButtonActive> strokeWidthDropdown;
    private boolean strokePressed = false;
    private static int strokeWidth;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;

    public MyStrokeWidth(int x, int y, int width, int height) {
        super(x, y, width, height);
        stroke = new ButtonActive(x+14, y+22, 64, 84, false, new ImageIcon("strokeWidth.png").getImage(), true);

        strokeWidthDropdown = new ArrayList<>();

        strokeWidthDropdown.add(new ButtonActive(x+11, y+110, 142, 44, false, new ImageIcon("strokeWidthSize01.jpg").getImage(), new ImageIcon("strokeWidthSize01Pressed.jpg").getImage(), 1));
        strokeWidthDropdown.add(new ButtonActive(x+11, y+154, 142, 44, false, new ImageIcon("strokeWidthSize03.jpg").getImage(), new ImageIcon("strokeWidthSize03Pressed.jpg").getImage(), 3));
        strokeWidthDropdown.add(new ButtonActive(x+11, y+198, 142, 44, false, new ImageIcon("strokeWidthSize05.jpg").getImage(), new ImageIcon("strokeWidthSize05Pressed.jpg").getImage(), 5));
        strokeWidthDropdown.add(new ButtonActive(x+11, y+242, 142, 44, false, new ImageIcon("strokeWidthSize08.jpg").getImage(), new ImageIcon("strokeWidthSize08Pressed.jpg").getImage(), 8));

        strokeWidth = 1;


    }

    public static int getStrokeWidth() {
        return strokeWidth;
    }

    public void paint(Graphics g, ImageObserver observer){
        super.paint(g);

        stroke.paint(g, observer);

        Font font = new Font("Arial", Font.PLAIN, 14);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Size", x+30, 138);

        if(showStrokeDropdown){
            for (ButtonActive b : strokeWidthDropdown){
                b.paint(g, observer);
            }
        }

        if(showToolTip) myToolTip.paint(g);
    }

    @Override
    public void onClick(int x, int y) {
        if(stroke.IsClicked(x, y)) {
            strokePress = true;
            strokePressed = true;
            showStrokeDropdown = true;
        }else {
            strokePress = false;
            showStrokeDropdown = false;
        }


        if(strokePressed) {
            if (strokeWidthDropdown.get(0).IsClicked(x, y)) {
                strokeWidth = strokeWidthDropdown.get(0).getStrokeWidth();
                strokePressed = false;
                showToolTip = false;
            }
            if (strokeWidthDropdown.get(1).IsClicked(x, y)) {
                strokeWidth = strokeWidthDropdown.get(1).getStrokeWidth();
                strokePressed = false;
                showToolTip = false;
            }
            if (strokeWidthDropdown.get(2).IsClicked(x, y)) {
                strokeWidth = strokeWidthDropdown.get(1).getStrokeWidth();
                strokePressed = false;
                showToolTip = false;
            }
            if (strokeWidthDropdown.get(3).IsClicked(x, y)) {
                strokeWidth = strokeWidthDropdown.get(3).getStrokeWidth();
                strokePressed = false;

                showToolTip = false;
            }
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
        if(stroke.isPressed(x, y)) {
            stroke.setPressed(true);
            myToolTip = MyToolTip.getInstance(x, y, "Current Stroke Width: "+ getStrokeWidth());
            showToolTip = true;
        } else{
            if(!strokePress) stroke.setPressed(false);
        }

        for (ButtonActive b : strokeWidthDropdown){
            b.IsClicked(x, y);
            if(b.isPressed(x, y) && stroke.getPressed()){
                myToolTip = MyToolTip.getInstance(x, y, "Stroke Width: "+ b.getStrokeWidth());
                showToolTip = true;
            }
        }

    }
}
