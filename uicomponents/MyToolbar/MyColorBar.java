package uicomponents.MyToolbar;

import swigncomponents.Board;
import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonGradient;
import uicomponents.MyButtons.ButtonToggle;
import uicomponents.MyButtons.ButtonToggleColor;
import uicomponents.MyButtons.ColorButton;
import uicomponents.MyToolTip.MyToolTip;
import uicomponents.MyWindow.MyGradientWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

/**
 * This class creates color bar
 */

public class MyColorBar extends MyRectangle implements PortionListener {

    private ArrayList<ColorButton> colorButtons = new ArrayList<>();
    private static ButtonToggleColor color1;
    private static ButtonToggleColor color2;
    private ButtonGradient gradient;
    private MyGradientWindow gradientWindow;
    private Color customColor = Color.LIGHT_GRAY;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;
    int i = 8;
    public static boolean showGradientWindow = false;
    private int buttonSize = 30;
    private int button1Size = 80;
    public MyColorBar(int x, int y, int width, int height) {
        super(x, y, width, height);

        color1 = new ButtonToggleColor(x+32, y+16, button1Size, button1Size, false, Color.BLACK);
        color2 = new ButtonToggleColor(x+64 + button1Size, y+16, button1Size, button1Size, false, Color.WHITE);

        gradient = new ButtonGradient(x+(width*3)/7 + (3 * buttonSize)+94, y+16, button1Size, button1Size, false, new ImageIcon("gradient.jpg").getImage(), new ImageIcon("gradient.jpg").getImage());
        gradientWindow = MyGradientWindow.getInstance(340, 170, 400, 340);

        colorButtons.add(new ColorButton(x+(width*3)/7+18, y+8, buttonSize, buttonSize, false, Color.BLACK));
        colorButtons.add(new ColorButton(x+(width*3)/7 + (1 * buttonSize)+22, y+8, buttonSize, buttonSize, false, Color.WHITE));
        colorButtons.add(new ColorButton(x+(width*3)/7 + (2 * buttonSize)+26, y+8, buttonSize, buttonSize, false, Color.RED));
        colorButtons.add(new ColorButton(x+(width*3)/7 + (3 * buttonSize)+30, y+8, buttonSize, buttonSize, false, Color.BLUE));
        colorButtons.add(new ColorButton(x+(width*3)/7+18, y+16+buttonSize, buttonSize, buttonSize, false, Color.CYAN));
        colorButtons.add(new ColorButton(x+(width*3)/7+ (1 * buttonSize)+22, y+16+buttonSize , buttonSize, buttonSize, false, Color.GREEN));
        colorButtons.add(new ColorButton(x+(width*3)/7+ (2 * buttonSize)+26, y+16+buttonSize , buttonSize, buttonSize, false, Color.PINK));
        colorButtons.add(new ColorButton(x+(width*3)/7+ (3 *buttonSize)+30, y+16+buttonSize , buttonSize, buttonSize, false, Color.MAGENTA));
        colorButtons.add(new ColorButton(x+(width*3)/7+18, y+24+2*buttonSize , buttonSize, buttonSize, false, Color.LIGHT_GRAY));
        colorButtons.add(new ColorButton(x+(width*3)/7+(1 * buttonSize)+22, y+24+2*buttonSize , buttonSize, buttonSize, false, Color.LIGHT_GRAY));
        colorButtons.add(new ColorButton(x+(width*3)/7+(2 * buttonSize)+26, y+24+2*buttonSize , buttonSize, buttonSize, false, Color.LIGHT_GRAY));
        colorButtons.add(new ColorButton(x+(width*3)/7+(3 * buttonSize)+30, y+24+2*buttonSize , buttonSize, buttonSize, false, Color.LIGHT_GRAY));
    }

    public static Color getStrokeColor(){
        return color1.getBoxColor();
    }

    public static Color getFillColor(){
        return color2.getBoxColor();
    }

    public void paint(Graphics graphics, ImageObserver observer){
        super.paint(graphics);

        Font font = new Font("Arial", Font.PLAIN, 16);
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        graphics.drawString("Color 1", x+44, 150);
        graphics.drawString("Color 2", x+76 + button1Size, 150);
        graphics.drawString("Edit colors", x+(width*3)/7 + (3 * buttonSize)+94, 150);

        color1.paint(graphics, observer);
        color2.paint(graphics, observer);

        gradient.paint(graphics, observer);

        for (ColorButton b : colorButtons){
            b.paint(graphics, observer);
        }

        if(showGradientWindow) gradientWindow.paint(graphics, observer);
        if(showToolTip) myToolTip.paint(graphics);

    }

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(int x, int y) {
        if(gradient.IsClicked(x, y)) {
            showGradientWindow = true;
            gradientWindow.setGradientPressed(true);
            gradientWindow.setClosed(false);
        }

        gradientWindow.onClick(x, y);

        if(gradientWindow.gradientSelect.IsClicked(x, y ) && gradientWindow.isGradientPressed()) {
            if (i > colorButtons.size() - 1) i = 8;
            colorButtons.get(i).setBoxColor(gradientWindow.getCurrentColor());
            i++;
            gradientWindow.setGradientPressed(false);
        }


        if(color1.IsClicked(x, y)){
            color1.setPressed(true);
            color2.setPressed(false);
        }else if(color2.IsClicked(x, y)){
            color2.setPressed(true);
            color1.setPressed(false);
        }

        for(ColorButton b : colorButtons){
            if(b.isPressed(x, y)){
                if(color1.getPressed()) color1.setBoxColor(b.getBoxColor());
                else if(color2.getPressed()) color2.setBoxColor(b.getBoxColor());
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
        if(!Board.windowOpened) {
            showToolTip = false;

            for (ColorButton b : colorButtons) {
                if (b.isPressed(x, y)) {
                    b.setPressed(true);
                    myToolTip = MyToolTip.getInstance(x, y, "[" + b.getBoxColor().getRed() + ", " + b.getBoxColor().getGreen() + ", " + b.getBoxColor().getBlue() + "]");
                    showToolTip = true;
                }else b.setPressed(false);
            }

            if (color1.IsClicked(x, y)) {
                myToolTip = MyToolTip.getInstance(x, y, "[" + color1.getBoxColor().getRed() + ", " + color1.getBoxColor().getGreen() + ", " + color1.getBoxColor().getBlue() + "]");
                showToolTip = true;
            }

            if (color2.IsClicked(x, y)) {
                myToolTip = MyToolTip.getInstance(x, y, "[" + color2.getBoxColor().getRed() + ", " + color2.getBoxColor().getGreen() + ", " + color2.getBoxColor().getBlue() + "]");
                showToolTip = true;
            }


            if (gradient.IsClicked(x, y)) {
                myToolTip = MyToolTip.getInstance(x, y, "Edit Colors");
                showToolTip = true;
            }
        }


        gradientWindow.onMove(x, y);
    }

    public void onDrag(int x, int y){
        gradientWindow.onDrag(x, y);
    }
}
