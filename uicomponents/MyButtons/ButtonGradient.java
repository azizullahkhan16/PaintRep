package uicomponents.MyButtons;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class creates button for gradient
 */

public class ButtonGradient extends MyButton{

    private Image imagePressed;
    private Image imageUnpressed;
    private Image current;
    private boolean isPressedFirst = false;
    public ButtonGradient(int x, int y, int width, int height, boolean isPressed, Image imagePressed, Image imageUnpressed) {
        super(x, y, width, height, isPressed);
        this.imagePressed = imagePressed;
        this.imageUnpressed = imageUnpressed;
        current =this.imageUnpressed;
    }

    @Override
    public void paint(Graphics graphics, ImageObserver imageObserver) {
        if (isPressed) graphics.setColor(Color.GRAY);
        else graphics.setColor(Color.WHITE);
        graphics.fillRect(x-2, y-2, width+4, height+4);
        graphics.drawImage(current, x, y, imageObserver);

    }

    public boolean IsClicked(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            isPressed = true;
            return true;
        }else {
            isPressed = false;
            return false;
        }
    }
}
