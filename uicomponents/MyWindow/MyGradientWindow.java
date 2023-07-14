package uicomponents.MyWindow;

import swigncomponents.Board;
import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonActive;
import uicomponents.MyButtons.ButtonGradientScheme;
import uicomponents.MyButtons.ButtonToggleColor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class creates gradient window
 */

public class MyGradientWindow extends MyWindow implements PortionListener {

    private boolean isClosed = false;
    private boolean gradientPressed = false;
    private Color currentColor;
    private ButtonGradientScheme gradientScheme;

    private ButtonToggleColor displayColor;
    protected ButtonActive close;


    public ButtonActive gradientSelect;


    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    private MyGradientWindow(int x, int y, int width, int height) {
        super(x, y, width, height);

        gradientScheme = new ButtonGradientScheme(x+width/3 +16, y+24+height/15, 240, 240, false);
        displayColor = new ButtonToggleColor(x+8, y+24+height/15, width*1/5, width*1/5, false, Color.getHSBColor(0, 1, 1));

        close = new ButtonActive(x+width-width/12, y+8, width/12, height/12, false, new ImageIcon("closeUnclicked.png").getImage());
        gradientSelect = new ButtonActive(x+4, y+height-height/8, width-8, height/9, false, "Add to Custom Colors", Color.BLACK, true, true);
    }

    public static MyGradientWindow getInstance(int x, int y, int width, int height){
        return new MyGradientWindow(x, y, width, height);
    }


    public void paint(Graphics g, ImageObserver observer){
        if(!isClosed) {
            super.paint(g, observer);
            gradientSelect.paint(g, observer);
            close.paint(g, observer);
            gradientScheme.paint(g, observer);

            displayColor.paint(g, observer);
            Font font = new Font("Arial", Font.PLAIN, 14);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Color|Solid", x+14, y+38+height/15 + width*1/5);


            font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Edit Colors", x+16, y+24);

            font = new Font("Arial", Font.PLAIN, 16);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Red:", x+8, y +height/2+16);
            g.setColor(Color.BLACK);
            g.fillRect(x+63, y+height/2-20+15, 32, 30);
            g.setColor(Color.WHITE);
            g.fillRect(x+64, y+height/2-20+16, 30, 28);
            g.setColor(Color.BLACK);
            if(displayColor.getBoxColor() != null) g.drawString(String.valueOf(displayColor.getBoxColor().getRed()), x+64, y+height/2+16);

            g.drawString("Green:", x+8, y +height/2+56);
            g.setColor(Color.BLACK);
            g.fillRect(x+63, y+height/2+56-21, 32, 30);
            g.setColor(Color.WHITE);
            g.fillRect(x+64, y+height/2+56-20, 30, 28);
            g.setColor(Color.BLACK);
            if(displayColor.getBoxColor() != null) g.drawString(String.valueOf(displayColor.getBoxColor().getGreen()), x+64, y+height/2+56);

            g.drawString("Blue:", x+8, y +height/2+96);
            g.setColor(Color.BLACK);
            g.fillRect(x+63, y+height/2+96-21, 32, 30);
            g.setColor(Color.WHITE);
            g.fillRect(x+64, y+height/2+96-20, 30, 28);
            g.setColor(Color.BLACK);
            if(displayColor.getBoxColor() != null) g.drawString(String.valueOf(displayColor.getBoxColor().getBlue()), x+64, y+height/2+96);
        }
    }

    public Color getCurrentColor(){
        return currentColor;
    }
    public boolean isClosed() { return isClosed;}

    public boolean isGradientPressed() {return gradientPressed;}
    public void setGradientPressed(boolean pressed) { gradientPressed = pressed;}

    @Override
    public void onClick(int x, int y) {
        if (gradientPressed) {

            if (close.IsClicked(x, y)) {
                isClosed = true;
                gradientPressed = false;
            }
            if (gradientSelect.IsClicked(x, y)) {
                isClosed = true;
                currentColor = displayColor.getBoxColor();
            }

            if (gradientScheme.IsClicked(x, y)) {
                displayColor.setBoxColor(gradientScheme.getCurrentColor());
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
        if(gradientPressed) {
            if (x < this.x || x > this.x + width || y < this.y || y > this.y + height) {
                Board.windowOpened = true;
                return;
            } else Board.windowOpened = false;
        }
        if(gradientSelect.isPressed(x, y)){
            gradientSelect.setPressed(true);
        } else {
            gradientSelect.setPressed(false);
        }
        if(close.isPressed(x, y)) close.setPressed(true);
        else close.setPressed(false);
    }

    public void onDrag(int x, int y){
        if(gradientScheme.IsClicked(x, y)){
            displayColor.setBoxColor(gradientScheme.getCurrentColor());
        }
    }
}
