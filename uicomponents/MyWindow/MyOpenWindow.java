package uicomponents.MyWindow;

import swigncomponents.Board;
import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonActive;
import uicomponents.MyButtons.ButtonToggle;
import uicomponents.MyToolbar.MyLayerBar;
import uicomponents.MyToolbar.MyMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;

/**
 * This class creates open window
 */

public class MyOpenWindow extends MyWindow implements PortionListener{

    private boolean isClosed = false;

    protected ButtonActive close;
    private ButtonActive select;
    private static String[] fileNames;
    private boolean press = false;
    private boolean openButtonPressed = false;
    private static int buttonHeight;
    private static int fontSize;

    private static ArrayList<ButtonToggle> fileNamesButton;

    public MyOpenWindow(int x, int y, int width, int height) {
        super(x, y, width, height);
        getFileNames();

        fileNamesButton = new ArrayList<>(fileNames.length);

        buttonHeight = 32;
        fontSize = 20;

        if(y+(height/5)+buttonHeight*fileNames.length > y+height-height/8) {
            buttonHeight = buttonHeight/2;
            fontSize = fontSize-8;
        }

        for (int i = 0; i < fileNames.length; i++) {
            fileNamesButton.add(new ButtonToggle(x+32, y+(height/5)+(i*buttonHeight)-(2*i), width-64, buttonHeight, false, fileNames[i], fontSize));
        }


        close = new ButtonActive(x+width-width/12, y+8, width/12, height/12, false, new ImageIcon("closeUnclicked.png").getImage());
        select = new ButtonActive(x+width-width/4, y+height-height/8, width/5, height/10, false, "Open", Color.BLACK, true, true);
    }


    public void refreshFileNames(){
        getFileNames();

        fileNamesButton = new ArrayList<>(fileNames.length);

        if(y+(height/5)+buttonHeight*fileNames.length > y+height-height/8) {
            buttonHeight = buttonHeight/2;
            fontSize = fontSize-8;
        }

        for (int i = 0; i < fileNames.length; i++) {
            fileNamesButton.add(new ButtonToggle(x+32, y+(height/5)+(i*buttonHeight)-(2*i), width-64, buttonHeight, false, fileNames[i], fontSize));
        }
    }

    public void paint(Graphics graphics, ImageObserver observer){
        if(!isClosed) {
            super.paint(graphics, observer);

            Font font = new Font("Arial", Font.BOLD, 20);
            graphics.setFont(font);
            graphics.setColor(Color.BLACK);
            graphics.drawString("Open", x+32, y+24);


            close.paint(graphics, observer);
            select.paint(graphics, observer);

            //refreshFileNames();
            for (ButtonToggle b : fileNamesButton){
                b.paint(graphics, observer);
            }


        }

    }


    public static void getFileNames(){

        // Create a File object for the folder
        File folder = new File("openFiles");

        // Get the list of files in the folder
        File[] files = folder.listFiles();

        // Create an array of strings to store the file names
        fileNames = new String[files.length];

        // Loop through the files and store their names in the array
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                fileNames[i] = files[i].getName();
            }
        }

    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public boolean isOpenButtonPressed() {return openButtonPressed;}
    public void setOpenButtonPressed(boolean openButtonPressed) {this.openButtonPressed = openButtonPressed;}

    @Override
    public void onClick(int x, int y) {
        if(openButtonPressed) {
            for (ButtonToggle b : fileNamesButton) {
                if (b.isPressed(x, y)) {
                    for (ButtonToggle b1 : fileNamesButton) {
                        b1.setPressed(false);
                    }
                    b.setPressed(true);
                    press = true;
                }
            }
            if (close.IsClicked(x, y)) {
                isClosed = true;
                openButtonPressed = false;
                MyMenuBar.setShowOpenWindow(false);
            }
            if (select.IsClicked(x, y)) {
                if (press) {
                    isClosed = true;
                    openButtonPressed = false;
                    MyMenuBar.setShowOpenWindow(false);
                    for (ButtonToggle b1 : fileNamesButton) {
                        if(b1.getPressed()) {
                            MyLayerBar.loadData(b1.getText());
                        }
                        b1.setPressed(false);
                    }
                    press = false;
                } else isClosed = false;
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
        if(openButtonPressed) {
            if (x < this.x || x > this.x + width || y < this.y || y > this.y + height) {
                Board.windowOpened = true;
                return;
            } else Board.windowOpened = false;
        }

        if(select.isPressed(x, y))select.setPressed(true);
        else select.setPressed(false);

        if(close.isPressed(x, y)) close.setPressed(true);
        else close.setPressed(false);

    }
}