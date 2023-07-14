package uicomponents.MyToolbar;
import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonActive;
import uicomponents.MyButtons.ButtonLayer;
import uicomponents.MyLinkedList.MyLinkedList;
import uicomponents.MyLinkedList.MyRedoStack;
import uicomponents.MyLinkedList.MyUndoStack;
import uicomponents.MyToolTip.MyToolTip;
import uicomponents.MyWindow.MyOpenWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class creates layer bar
 */

public class MyLayerBar extends MyRectangle implements PortionListener {

    private ButtonActive add;
    private ButtonActive remove;
    private ButtonActive up;
    private ButtonActive down;

    private static MyLinkedList layersButton;
    private static ButtonLayer currentButton;
    private static int currentIndex;
    private static int i = 1;
    private static int layerNum = 1;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;
    private boolean displayMessage;
    private ImageIcon alert;


    public MyLayerBar(int x, int y, int width, int height) {

        super(x, y, width, height);

        add = new ButtonActive(x+8, y+64, 36, 36, false, new ImageIcon("addButton.png").getImage(), true);
        remove = new ButtonActive(x+16+width/5, y+64, 36, 36, false, new ImageIcon("removeButton.png").getImage(), true);
        up = new ButtonActive(x+24+(2*width/5), y+64, 36, 36, false, new ImageIcon("upButton.png").getImage(), true);
        down = new ButtonActive(x+32+(3*width/5), y+64, 36, 36, false, new ImageIcon("downButton.png").getImage(), true);

        alert = new ImageIcon("alert.png");

        currentIndex = 1;
        i = 1;
        layerNum = 1;

        layersButton = new MyLinkedList(8);

        currentButton = null;

        layersButton.add(new ButtonLayer(x+8, y+112, width-16, height/12, true, "Layer " + layerNum, layerNum, new MyUndoStack(), new MyRedoStack()));
        layerNum++;
    }

    public MyLayerBar(){}


    public void swap(ButtonLayer b1, ButtonLayer b2){
        int y = b1.getY();
        b1.setY(b2.getY());
        b2.setY(y);
    }
    public static MyLinkedList getLayersButton() {
        return layersButton;
    }

    public static ButtonLayer getCurrentButton() {
        return currentButton;
    }

    public void paint(Graphics g, ImageObserver observer){
        super.paint(g);
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width-2, 48);

        g.drawImage(new ImageIcon("layer.png").getImage(), x+16, y+4, observer);

        Font font = new Font("Arial", Font.BOLD, 28);
        g.setFont(font);
        g.setColor(Color.BLACK);
        FontMetrics m = g.getFontMetrics();
        int s_wdith = m.stringWidth("Layers");
        int s_height = m.getAscent() - m.getDescent();
        g.drawString("Layers", x + (width-2) / 2 - s_wdith*1/3, y + 48 / 2 + s_height / 2);

        add.paint(g, observer);
        remove.paint(g, observer);
        up.paint(g, observer);
        down.paint(g, observer);

        for (int j = 0; j < i; j++) {
            getLayersButton().getIndex(j).paint(g, observer);
        }


        if(displayMessage) {
            font = new Font("Arial", Font.BOLD, 16);
            g.setFont(font);
            g.setColor(Color.RED);
            g.drawString("Select a layer first!", this.x+38, height+18);

            g.drawImage(alert.getImage(), this.x+10, height, observer);
        }

        if(showToolTip) myToolTip.paint(g);

    }

    public void setDisplayMessage(boolean displayMessage){
        this.displayMessage = displayMessage;
    }

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    @Override
    public void onClick(int x, int y) {
        if(MyMenuBar.isNewFileOpened()){
            newFileOpened();
            MyMenuBar.setNewFileOpened(false);
        }

        for (int j = 0; j < i; j++) {
            if(layersButton.getIndex(j).IsClicked(x, y)) {
                currentIndex = j;
                for (int k = 0; k < i; k++) {
                    layersButton.getIndex(k).setPressed(false);
                    layersButton.getIndex(k).setCurrentUnpressed();
                }
                layersButton.getIndex(j).setPressed(true);
                layersButton.getIndex(j).setCurrentPressed();
                currentButton = layersButton.getIndex(j);
            }

            if(layersButton.getIndex(j).isPressedFirst) currentButton = null;
        }

        if(add.IsClicked(x, y) && !layersButton.isFull()){
            System.out.println(layerNum);
            layersButton.add(new ButtonLayer(this.x+8, this.y+112+i*(height/12)-(2*i), width-16, height/12, false, "Layer " + layerNum, layerNum, new MyUndoStack(), new MyRedoStack()));
            i++; layerNum++;
        }

        if(remove.IsClicked(x, y) && !layersButton.isEmpty() && i != 1){
            if (currentButton != null) {
                if(layersButton.getNext(currentButton) != null) {
                    for (int j = currentIndex; j <= i; j++) {
                        if (layersButton.getNext(layersButton.getIndex(j)) != null)
                            swap(currentButton, layersButton.getNext(layersButton.getIndex(j)));
                    }
                }
                layersButton.remove(currentButton);
                currentButton = null;
                i--;
            }
        }

        if(up.IsClicked(x, y) && currentButton != null){
            ButtonLayer b = layersButton.getPrevious(currentButton);
            if(!layersButton.isFirstElement(currentButton)) {
                swap(currentButton, b);
                currentIndex--;
            }
            layersButton.moveUp(currentButton);
        }

        if(down.IsClicked(x, y) && currentButton != null){
            ButtonLayer b = layersButton.getNext(currentButton);
            if (!layersButton.isLastElement(currentButton)) {
                swap(currentButton, b);
                currentIndex++;
            }
            layersButton.moveDown(currentButton);
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
        showToolTip = false;
        if(add.isPressed(x, y)) {
            myToolTip = MyToolTip.getInstance(x, y,  "Add Layer");
            showToolTip = true;
            add.setPressed(true);
        } else add.setPressed(false);


        if(remove.isPressed(x, y)) {
            myToolTip = MyToolTip.getInstance(x, y,  "Remove Layer");
            showToolTip = true;
            remove.setPressed(true);
        } else remove.setPressed(false);

        if(up.isPressed(x, y)) {
            myToolTip = MyToolTip.getInstance(x, y,  "Move Up");
            showToolTip = true;
            up.setPressed(true);
        } else up.setPressed(false);

        if(down.isPressed(x, y)) {
            myToolTip = MyToolTip.getInstance(x, y,  "Move Down");
            showToolTip = true;
            down.setPressed(true);
        } else down.setPressed(false);

        for (int j = 0; j < i; j++) {
            if(layersButton.getIndex(j).isPressed(x, y)) {
                if(layersButton.getIndex(j).getTick()) myToolTip = MyToolTip.getInstance(x, y,  "Layer " + layersButton.getIndex(j).getLayerNum() + " Selected");
                else myToolTip = MyToolTip.getInstance(x, y,  "Layer " + layersButton.getIndex(j).getLayerNum());
                showToolTip = true;
                layersButton.getIndex(j).setPressed(true);
            }
            else layersButton.getIndex(j).setPressed(false);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 's' && !MyMenuBar.isShowOpenWindow()) {
            saveData();
        }

        if (e.getKeyChar() == 'n' && !MyMenuBar.isShowOpenWindow()) {
            newFileOpened();
        }

    }

    public void newFileOpened(){
        currentIndex = 1;
        i = 1;
        layerNum = 1;

        layersButton = new MyLinkedList(8);

        currentButton = null;

        layersButton.add(new ButtonLayer(x+8, y+112, width-16, height/12, true, "Layer " + layerNum, layerNum, new MyUndoStack(), new MyRedoStack()));
        layerNum++;
    }

    public static void saveData(){
        try {
            // Get the current date and time
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String fileName = dateFormat.format(currentDate) + ".ser";

            FileOutputStream fileOutputStream = new FileOutputStream("openFiles/"+fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(layersButton);
            objectOutputStream.writeObject(currentButton);
            objectOutputStream.writeObject(currentIndex);
            objectOutputStream.writeObject(i);
            objectOutputStream.writeObject(layerNum);
            objectOutputStream.close();
            fileOutputStream.close();System.out.println("ArrayList is serialized and saved to " + fileName);
        } catch (IOException s) {
            s.printStackTrace();
        }
    }

    public static void loadData(String file){
        try {
            layersButton = new MyLinkedList(8);
            FileInputStream fileInputStream = new FileInputStream("openFiles/"+ file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            layersButton = (MyLinkedList) objectInputStream.readObject();
            currentButton = (ButtonLayer) objectInputStream.readObject();
            currentIndex = (int) objectInputStream.readObject();
            i = (int) objectInputStream.readObject();
            layerNum = (int) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("ArrayList is deserialized from "+ file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
