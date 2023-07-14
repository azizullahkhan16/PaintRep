package uicomponents.MyButtons;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class creates the gradient scheme
 */

public class ButtonGradientScheme extends MyButton{

    ButtonGradientColor[][] gradientColors;

    private Color currentColor;


    public ButtonGradientScheme(int x, int y, int width, int height, boolean isPressed) {
        super(x, y, width, height, isPressed);
        
        gradientColors = new ButtonGradientColor[240][241];

        for (int i = 0; i < 240; i++) {
            for (int j = 240; j >=0; j--) {
                gradientColors[i][240-j] = new ButtonGradientColor(x+i, y+240-j, 1, 1, false, Color.getHSBColor((float) i/240, (float) j/240, 1));

            }
            
        }
        
        

    }

    @Override
    public void paint(Graphics graphics, ImageObserver imageObserver) {
        for (int i = 0; i < 240; i++) {
            for (int j = 0; j < 240; j++) {
                gradientColors[i][j].paint(graphics, imageObserver);
            }

        }

    }

    public void setPressed(boolean pressed){ this.isPressed = pressed;}
    public boolean getPressed() {return this.isPressed;}

    public Color getCurrentColor(){return currentColor;}

    public boolean isPressed(int x, int y) {
        if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        } else return false;
    }

    public boolean IsClicked(int x, int y) {

        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {

            for (int i = 0; i < 240; i++) {
                for (int j = 0; j < 240; j++) {
                    if(gradientColors[i][j].isPressed(x, y)) {
                        currentColor = gradientColors[i][j].getBoxColor();
                    }
                }
            }
            return true;
        }

        return false;

    }
}
