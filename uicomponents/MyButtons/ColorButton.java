package uicomponents.MyButtons;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class creates color buttons
 */

public class ColorButton extends MyButton{

    private Color boxColor;

    public ColorButton(int x, int y, int width, int height, boolean isPressed, Color boxColor) {
        super(x, y, width, height, isPressed);
        this.boxColor = boxColor;
    }

    @Override
    public void paint(Graphics graphics, ImageObserver imageObserver) {
        if (isPressed) graphics.setColor(Color.GRAY);
        else graphics.setColor(Color.white);

        graphics.fillRect(x, y, width, height);
        graphics.setColor(boxColor);
        graphics.fillRect(x+2, y+2, width-4, height-4);
    }

    public void setPressed(boolean isPressed) { this.isPressed = isPressed;}
    public boolean getPressed() { return isPressed;}

    public Color getBoxColor() {
        return boxColor;
    }
    public void setBoxColor(Color color){ this.boxColor = color;}

    public void IsClicked(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            isPressed = true;
        }else isPressed = false;
    }

    public boolean isPressed(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }else return false;
    }
}
