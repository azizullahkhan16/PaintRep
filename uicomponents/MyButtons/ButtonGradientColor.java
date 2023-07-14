package uicomponents.MyButtons;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class is to create buttons inside of gradient
 */

public class ButtonGradientColor extends MyButton{

    private Color boxColor;

    public ButtonGradientColor(int x, int y, int width, int height, boolean isPressed, Color boxColor) {
        super(x, y, width, height, isPressed);
        this.boxColor = boxColor;
    }

    @Override
    public void paint(Graphics graphics, ImageObserver imageObserver) {
        graphics.setColor(boxColor);
        graphics.fillRect(x, y, width, height);

    }

    public void setPressed(boolean pressed){ this.isPressed = pressed;}
    public boolean getPressed(){ return isPressed;}

    public void setBoxColor(Color color){ this.boxColor = color;}


    public Color getBoxColor() {return boxColor;}

    public boolean isPressed(int x, int y){
        if(x >= this.x && x < this.x + width && y >= this.y && y < this.y + height) {
            return true;
        }else return false;
    }

    public Color IsClicked(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return boxColor;
        }else return null;
    }
}
