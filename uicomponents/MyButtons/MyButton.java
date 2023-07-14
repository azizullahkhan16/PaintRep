package uicomponents.MyButtons;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serial;
import java.io.Serializable;

/**
 * This is the abstract class for button
 */

public abstract class MyButton implements Serializable {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected boolean isPressed;

    public MyButton(int x, int y, int width, int height, boolean isPressed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isPressed = isPressed;
    }

    public abstract void paint(Graphics graphics, ImageObserver imageObserver);

}
