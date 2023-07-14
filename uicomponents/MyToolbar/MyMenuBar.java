package uicomponents.MyToolbar;

import swigncomponents.Board;
import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonActive;
import uicomponents.MyToolTip.MyToolTip;
import uicomponents.MyWindow.MyOpenWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Shortcuts
 * press 'n' for new window
 * press 'o' for open window
 * press 's' for save window
 * press 'z' for undo
 * press 'r' for redo
 */

public class MyMenuBar extends MyRectangle implements PortionListener, Serializable {

    private ButtonActive fileButton;
    private ButtonActive editButton;
    private ArrayList<ButtonActive> fileDropDown = new ArrayList<>();
    private ArrayList<ButtonActive> editDropDown = new ArrayList<>();
    private MyOpenWindow myOpenWindow;

    private boolean showFileDropdown = false;
    private boolean showEditDropdown = false;
    private boolean filePress = false;
    private boolean filePressed = false;
    private boolean editPress = false;
    private boolean editPressed = false;
    private static boolean showOpenWindow = false;
    private boolean showSaveWindow = false;
    private boolean showNewWindow = false;
    private boolean showUndo = false;
    private boolean showRedo = false;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;

    private static boolean newFileOpened = false;
    private ImageIcon buttonPressed;


    public MyMenuBar(int x, int y, int width, int height) {
        super(x, y, width, height);

        fileButton = new ButtonActive(0,0 , 64, 32, false, "File", Color.BLACK, true);
        editButton = new ButtonActive(64,0 , 64, 32, false, "Edit", Color.BLACK, true);

        fileDropDown.add(new ButtonActive(0, 32, 128, 44, false, new ImageIcon("newUnclicked.jpg").getImage(), new ImageIcon("newClicked.jpg").getImage(), "New(n)"));
        fileDropDown.add(new ButtonActive(0, 76, 128, 44, false, new ImageIcon("openUnclicked.jpg").getImage(), new ImageIcon("openClicked.jpg").getImage(), "Open(o)"));
        fileDropDown.add(new ButtonActive(0, 120, 128, 44, false, new ImageIcon("saveUnclicked.jpg").getImage(), new ImageIcon("saveClicked.jpg").getImage(), "Save(s)"));

        editDropDown.add(new ButtonActive(68, 32, 128, 44, false, new ImageIcon("undoUnclicked.png").getImage(), new ImageIcon("undoClicked.png").getImage(), "Undo(z)"));
        editDropDown.add(new ButtonActive(68, 76, 128, 44, false, new ImageIcon("redoUnclicked.png").getImage(), new ImageIcon("redoClicked.png").getImage(), "Redo(r)"));

        myOpenWindow = new MyOpenWindow(340, 170, 400, 340);

        buttonPressed = new ImageIcon("button.png");
    }



    public void paint(Graphics g, ImageObserver observer){
        if(showFileDropdown){
            for (ButtonActive b : fileDropDown){
                b.paint(g, observer);
            }
        }else if(showEditDropdown){
            for (ButtonActive b : editDropDown){
                b.paint(g, observer);
            }
        }
        super.paint(g);

        fileButton.paint(g, observer);
        editButton.paint(g, observer);

        if(showOpenWindow) myOpenWindow.paint(g, observer);
        if(showNewWindow){
            Font font = new Font(Font.SERIF, Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("New File", 24, 675);
            g.drawImage(buttonPressed.getImage(), 1, 655, observer);
        }
        if(showSaveWindow){
            Font font = new Font(Font.SERIF, Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("File Saved", 24, 675);
            g.drawImage(buttonPressed.getImage(), 1, 655, observer);
        }
        if(showUndo){
            Font font = new Font(Font.SERIF, Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Undo Pressed!", 24, 675);
            g.drawImage(buttonPressed.getImage(), 1, 655, observer);
        }
        if(showRedo){
            Font font = new Font(Font.SERIF, Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Redo Pressed!", 24, 675);
            g.drawImage(buttonPressed.getImage(), 1, 655, observer);
        }

        if(showToolTip) myToolTip.paint(g);

    }

    public boolean isPressed(int x ,int y){
        if(x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
            return true;
        }
        return false;
    }

    public static boolean isNewFileOpened() {return newFileOpened;}
    public static void setNewFileOpened(boolean newFileOpened) { MyMenuBar.newFileOpened = newFileOpened;}

    public boolean isFilePressed() {
        return filePressed;
    }

    public static boolean isShowOpenWindow() {return showOpenWindow;}

    public static void setShowOpenWindow(boolean showOpenWindow) {MyMenuBar.showOpenWindow = showOpenWindow;}

    public boolean isFilePress() {
        return filePress;
    }

    @Override
    public void onClick(int x, int y) {
        showSaveWindow = false;
        showNewWindow = false;
        showUndo = false;
        showRedo = false;
        if(fileButton.IsClicked(x, y)) {
            filePress = true;
            filePressed = true;
            showEditDropdown = false;
            showFileDropdown = true;
        }else {
            filePress = false;
            showFileDropdown = false;
        }

        if(filePressed) {
            if (fileDropDown.get(0).IsClicked(x, y)) {
                showNewWindow = true;
                newFileOpened = true;
                filePressed = false;
                showToolTip = false;
            }
            if (fileDropDown.get(1).IsClicked(x, y)) {
                showOpenWindow = true;
                filePressed = false;
                showToolTip = false;
                myOpenWindow.setOpenButtonPressed(true);
                myOpenWindow.setClosed(false);
            }else showOpenWindow = false;
            if (fileDropDown.get(2).IsClicked(x, y)) {
                showSaveWindow = true;
                filePressed = false;
                showToolTip = false;
            }
        }

        myOpenWindow.onClick(x, y);

        if(editButton.IsClicked(x, y)) {
            editPress = true;
            editPressed = true;
            showFileDropdown = false;
            showEditDropdown = true;

        }else {
            editPress = false;
            showEditDropdown = false;
        }

        if(editPressed) {

            if (editDropDown.get(0).IsClicked(x, y)) {
                if(MyLayerBar.getCurrentButton() != null && !showOpenWindow) {
                    if (!MyLayerBar.getCurrentButton().getMyUndoStack().isEmpty()) {
                        MyLayerBar.getCurrentButton().getMyRedoStack().push(MyLayerBar.getCurrentButton().getMyUndoStack().pop());
                    }
                }
                showUndo = true;
                editPressed = false;
                showToolTip = false;
            }
            if (editDropDown.get(1).IsClicked(x, y)) {
                if(MyLayerBar.getCurrentButton() != null && !showOpenWindow) {
                    if (!MyLayerBar.getCurrentButton().getMyRedoStack().isEmpty()) {
                        MyLayerBar.getCurrentButton().getMyUndoStack().push(MyLayerBar.getCurrentButton().getMyRedoStack().pop());
                    }
                }
                showRedo = true;
                showOpenWindow = false;
                editPressed = false;
                showToolTip = false;
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

            if (fileButton.isPressed(x, y)) {
                fileButton.setPressed(true);
                editButton.setPressed(false);
                showEditDropdown = false;
            } else {
                if (!filePress) fileButton.setPressed(false);
            }

            if (editButton.isPressed(x, y) && !editPress) {
                editButton.setPressed(true);
                fileButton.setPressed(false);
                showFileDropdown = false;
            } else {
                if (!editPress) editButton.setPressed(false);

            }

            for (ButtonActive b : fileDropDown) {
                b.IsClicked(x, y);
                if (b.isPressed(x, y) && fileButton.getPressed()) {
                    myToolTip = MyToolTip.getInstance(x, y, b.getType());
                    showToolTip = true;
                }
            }
            for (ButtonActive b : editDropDown) {
                b.IsClicked(x, y);
                if (b.isPressed(x, y) && editButton.getPressed()) {
                    myToolTip = MyToolTip.getInstance(x, y, b.getType());
                    showToolTip = true;
                }
            }
        }

        myOpenWindow.onMove(x, y);
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'n'&& !showOpenWindow) {
            showNewWindow = false;
        }
        if (e.getKeyChar() == 's'&& !showOpenWindow) {
            myOpenWindow.refreshFileNames();
            showSaveWindow = false;
        }

        if (e.getKeyChar() == 'z') {
            if(MyLayerBar.getCurrentButton() != null && !showOpenWindow) {
                if (!MyLayerBar.getCurrentButton().getMyUndoStack().isEmpty()) {
                    MyLayerBar.getCurrentButton().getMyRedoStack().push(MyLayerBar.getCurrentButton().getMyUndoStack().pop());
                }
            }
            showUndo = false;
        }

        if (e.getKeyChar() == 'r') {
            if(MyLayerBar.getCurrentButton() != null && !showOpenWindow) {
                if (!MyLayerBar.getCurrentButton().getMyRedoStack().isEmpty()) {
                    System.out.println(1);
                    MyLayerBar.getCurrentButton().getMyUndoStack().push(MyLayerBar.getCurrentButton().getMyRedoStack().pop());
                }
            }
            showRedo = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'n'&& !showOpenWindow) {
            showNewWindow = true;
        }
        if (e.getKeyChar() == 'o'&& !showOpenWindow) {
            showOpenWindow = true;
            myOpenWindow.setClosed(false);
            myOpenWindow.setOpenButtonPressed(true);
        }
        if (e.getKeyChar() == 's' && !showOpenWindow) {
            showSaveWindow = true;
        }
        if (e.getKeyChar() == 'z'&& !showOpenWindow) {
            showUndo = true;
        }
        if (e.getKeyChar() == 'r'&& !showOpenWindow) {
            showRedo = true;
        }
    }
}
