package uicomponents.MyButtons;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class creates toggle buttons
 */
public class ButtonToggle extends MyButton {

    private Image current;
    private boolean isPressedFirst = false;
    private String type;
    private String text;
    private boolean withText = false;
    private int fontSize;

    public ButtonToggle(int x, int y, int width, int height, boolean isPressed, Image image, String type) {
        super(x, y, width, height, isPressed);
        this.current = image;
        this.type = type;
        this.withText = false;
    }

    public String getType() {
        return type;
    }

    public ButtonToggle(int x, int y, int width, int height, boolean isPressed, String text, int fontSize){
        super(x, y, width, height, isPressed);
        this.text = text;
        this.withText = true;
        this.fontSize = fontSize;

    }

    public String getText() { return text;}

    public void setPressed(boolean pressed){ this.isPressed = pressed;}
    public boolean getPressed(){ return isPressed;}

    @Override
    public void paint(Graphics graphics, ImageObserver imageObserver) {
        if(withText){
            graphics.setColor(Color.black);
            graphics.fillRect(x, y, width, height);
            if (isPressed) graphics.setColor(Color.GRAY);
            else graphics.setColor(Color.LIGHT_GRAY);


            graphics.fillRect(x+2, y+2, width-4, height-4);


            Font font = new Font("Arial", Font.PLAIN, fontSize);
            graphics.setFont(font);
            graphics.setColor(Color.BLACK);
            FontMetrics m = graphics.getFontMetrics();
            int s_wdith = m.stringWidth(text);
            int s_height = m.getAscent() - m.getDescent();
            graphics.drawString(text, x + width / 2 - s_wdith / 2, y + height / 2 + s_height / 2);

        }else {
            if (isPressed) {
                graphics.setColor(Color.BLACK);
                graphics.drawRect(x-3, y-3, width+6, height+6);
                graphics.setColor(Color.GRAY);
            } else graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(x-2, y-2, width+5, height+5);
            graphics.drawImage(current, x, y, imageObserver);
        }
    }

    public boolean isPressed(int x, int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height && !isPressedFirst) {
            return true;
        }else return false;
    }

    public boolean IsClicked(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height && !isPressedFirst) {
            isPressedFirst = true;
            return true;
        }else if(x > this.x && x < this.x + width && y > this.y && y < this.y + height && isPressedFirst) {
            isPressed = false;
            isPressedFirst = false;
            return false;
        }else return false;
    }
}
