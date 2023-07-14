package uicomponents.MyButtons;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This class creates active buttons
 */

public class ButtonActive extends MyButton{
    private Image imagePressed;
    private Image imageUnpressed;
    private String text;
    private Color textColor;

    private int strokeWidth;
    private boolean withText, close, isLayerButton, withStroke, isStrokeButton;
    private Image current;
    private String type;

    // This constructor is to create menu bar buttons
    public ButtonActive(int x, int y, int width, int height, boolean isPressed, Image imageUnpressed, Image imagePressed, String type) {
        super(x, y, width, height, isPressed);
        this.imagePressed = imagePressed;
        this.imageUnpressed = imageUnpressed;
        this.withText = false;
        this.close = false;
        this.isLayerButton = false;
        this.type = type;
        current = this.imageUnpressed;
    }

    public ButtonActive(int x, int y, int width, int height, boolean isPressed, Image imageUnpressed, Image imagePressed, int strokeWidth) {
        super(x, y, width, height, isPressed);
        this.imagePressed = imagePressed;
        this.imageUnpressed = imageUnpressed;
        this.strokeWidth = strokeWidth;
        this.withText = false;
        this.close = false;
        this.isLayerButton = false;
        current = this.imageUnpressed;
    }

    // This constructor is used to create close buttons
    public ButtonActive(int x, int y, int width, int height, boolean isPressed, Image imageUnpressed) {
        super(x, y, width, height, isPressed);
        this.imageUnpressed = imageUnpressed;
        this.withText = false;
        this.close = true;
        this.isLayerButton = false;
        current =this.imageUnpressed;
    }

    // This constructor is used to create layer buttons
    public ButtonActive(int x, int y, int width, int height, boolean isPressed, Image imageUnpressed, boolean isLayerButton) {
        super(x, y, width, height, isPressed);
        this.imageUnpressed = imageUnpressed;
        this.isLayerButton = isLayerButton;
        this.withText = false;
        this.close = false;
        current =this.imageUnpressed;
    }

    // This constructor is used to create buttons with text
    public ButtonActive(int x, int y, int width, int height, boolean isPressed, String text, Color textColor, boolean withText) {
        super(x, y, width, height, isPressed);
        this.text = text;
        this.textColor = textColor;
        this.withText = withText;
        this.withStroke = false;
        this.isLayerButton = false;
        this.close = false;
    }

    // This constructor is used to create buttons with text and stroke
    public ButtonActive(int x, int y, int width, int height, boolean isPressed, String text, Color textColor, boolean withText, boolean withStroke) {
        super(x, y, width, height, isPressed);
        this.text = text;
        this.textColor = textColor;
        this.withText = withText;
        this.withStroke = withStroke;
        this.isLayerButton = false;
        this.close = false;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public String getType() {
        return type;
    }

    public boolean getPressed(){
        return isPressed;
    }
    public void setPressed(boolean press){ this.isPressed = press; }

    @Override
    public void paint(Graphics graphics, ImageObserver imageObserver) {
        if(withText) {
            graphics.setColor(Color.black);
            graphics.fillRect(x, y, width, height);
            if (isPressed) graphics.setColor(Color.GRAY);
            else graphics.setColor(Color.LIGHT_GRAY);
            if(withStroke) graphics.fillRect(x+2, y+2, width-4, height-4);
            else graphics.fillRect(x, y, width, height-2);


            Font font = new Font("Arial", Font.PLAIN, 13);
            graphics.setFont(font);
            graphics.setColor(textColor);
            FontMetrics m = graphics.getFontMetrics();
            int s_wdith = m.stringWidth(text);
            int s_height = m.getAscent() - m.getDescent();
            graphics.drawString(text, x + width / 2 - s_wdith / 2, y + height / 2 + s_height / 2);
        }else if(close){
            if (isPressed) graphics.setColor(Color.RED);
            else graphics.setColor(Color.GRAY);
            graphics.fillRect(x-8, y-5, width, height);
            graphics.drawImage(current, x, y, imageObserver);
        }else if(isLayerButton){
            if (isPressed) {
                graphics.setColor(Color.BLACK);
                graphics.drawRect(x-4, y-4, width+8, height+8);
                graphics.setColor(Color.GRAY);
            }
            else graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(x-3, y-3, width+7, height+7);
            graphics.drawImage(current, x, y, imageObserver);
        }else {
            graphics.drawImage(current, x, y, imageObserver);
        }

    }
    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    public boolean IsClicked(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            isPressed = true;
            if(!isLayerButton) current = imagePressed;
            return true;
        }else {
            current = imageUnpressed;
            isPressed=false;
            return false;
        }
    }
}
