package uicomponents.MyToolbar;
import swigncomponents.PortionListener;
import uicomponents.MyButtons.ButtonActive;
import uicomponents.MyToolTip.MyToolTip;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static swigncomponents.Board.B_HEIGHT;
import static swigncomponents.Board.B_WIDTH;

public class MyGrid extends MyRectangle implements PortionListener {

    private ButtonActive grid;
    private boolean gridShow = false;
    private static int gridCount = 0;
    private MyToolTip myToolTip;
    private boolean showToolTip = false;

    private MyGrid(int x, int y, int width, int height) {
        super(x, y, width, height);

        grid = new ButtonActive(x+14, y+22, 64, 84, false, new ImageIcon("grid.png").getImage(), true);

    }

    public static MyGrid getInstance(int x, int y, int width, int height){
        return new MyGrid(x, y, width, height);

    }

    public void paint(Graphics g, ImageObserver observer){
        super.paint(g);

        grid.paint(g, observer);

        Font font = new Font("Arial", Font.PLAIN, 14);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Grid", x+30, 138);

        if(gridShow) gridDraw(g);
        if(showToolTip) myToolTip.paint(g);
    }

    public static void gridDraw(Graphics g){

        for (int i = 162; i < B_HEIGHT; i+=(gridCount*8)){
            g.setColor(Color.GRAY);
            g.drawLine(0, i,12*(B_WIDTH/14)-12, i);
        }

        for (int i = 0; i < 12*(B_WIDTH/14)-11; i+=(gridCount*8)){
            g.setColor(Color.GRAY);
            g.drawLine(i, 162, i, B_HEIGHT);
        }
    }

    @Override
    public void onClick(int x, int y) {
        if(grid.IsClicked(x, y)) {
            if(gridCount != 6) {
                gridCount ++;
                gridShow = true;
            } else {
                gridShow = false;
                gridCount = 0;
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
        showToolTip = false;
        if(grid.isPressed(x, y)) {
            grid.setPressed(true);
            myToolTip = MyToolTip.getInstance(x, y, "Draw Grid");
            showToolTip = true;
        }
        else grid.setPressed(false);

    }
}
