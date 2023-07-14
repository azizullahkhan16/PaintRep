package uicomponents.MyButtons;

import uicomponents.MyLinkedList.MyRedoStack;
import uicomponents.MyLinkedList.MyUndoStack;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * This class creates buttons for layers
 */

public class ButtonLayer extends MyButton implements Serializable {

    private ImageIcon imagePressed;
    private ImageIcon imageUnpressed;
    private ImageIcon current;
    private String text;
    public Boolean isPressedFirst= false;
    private int layerNum;
    private MyUndoStack myUndoStack;
    private MyRedoStack myRedoStack;
    public ButtonLayer(int x, int y, int width, int height, boolean isPressed, String text, int layerNum, MyUndoStack myUndoStack, MyRedoStack myRedoStack) {
        super(x, y, width, height, isPressed);
        this.imagePressed = new ImageIcon("checkPressed.png");
        this.imageUnpressed = new ImageIcon("checkUnpressed.png");
        this.current = this.imageUnpressed;
        this.text = text;
        this.layerNum = layerNum;
        this.myUndoStack = myUndoStack;
        this.myRedoStack = myRedoStack;
    }

    public MyUndoStack getMyUndoStack() {
        return myUndoStack;
    }

    public MyRedoStack getMyRedoStack() {
        return myRedoStack;
    }

    public int getLayerNum() {return layerNum;}

    public void setCurrentUnpressed() {
        this.current = imageUnpressed;
    }
    public void setCurrentPressed() {this.current = imagePressed;}

    public String getText() {
        return text;
    }

    @Override
    public void paint(Graphics graphics, ImageObserver imageObserver) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, width, height);
        if (isPressed) graphics.setColor(Color.GRAY);
        else graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(x+2, y+2, width-4, height-4);

        Font font = new Font("Arial", Font.PLAIN, 18);
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);
        FontMetrics m = graphics.getFontMetrics();
        int s_wdith = m.stringWidth(text);
        int s_height = m.getAscent() - m.getDescent();
        graphics.drawString(text, x + width / 2 - s_wdith*3/4, y + height / 2 + s_height / 2);

        graphics.drawImage(current.getImage(), x+height/4, y+height/4, imageObserver);

    }

    public boolean getPressed(){
        return isPressed;
    }
    public void setPressed(boolean press){ this.isPressed = press; }

    public boolean getTick(){
        if(current.equals(imagePressed)) return true;
        else return false;
    }

    public void setY(int y){ this.y = y;}
    public int getY(){ return this.y;}

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    public boolean IsClicked(int x, int y) {
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height && !isPressedFirst) {
            return true;
        }else if(x > this.x && x < this.x + width && y > this.y && y < this.y + height && isPressedFirst) {
            isPressedFirst = false;
            return false;
        }else return false;
    }
}
