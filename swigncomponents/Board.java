package swigncomponents;
import uicomponents.MyToolbar.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Board extends JPanel
        implements ActionListener , MouseInputListener{

    public static final int B_WIDTH = 1280;
    public static final int B_HEIGHT = 680;

    private final int DELAY = 10;
    private Timer timer;
    public static boolean windowOpened = false;

    private MyMenuBar myMenuBar;
    private MyColorBar myColorBar;
    private MyShapeBar myShapeBar;
    private MyLayerBar myLayerBar;
    private MyDrawingBoard myDrawingBoard;
    private MyCurves myCurves;
    private MyStrokeWidth myStrokeWidth;
    private MyGrid myGrid;
    private MyFreeDrawing myFreeDrawing;


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            myLayerBar.keyReleased(e);
            myMenuBar.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            myMenuBar.keyPressed(e);
        }
    }

    public Board() {

        initBoard();
    }

    private void InitializeAssets() {


    }

    private void initBoard() {

    	addMouseListener( this );
    	addMouseMotionListener( this );
    	addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setFocusable(true);

        InitializeAssets();
        myColorBar = new MyColorBar(6*(B_WIDTH/14)-10, 32, 6*(B_WIDTH/14), 130);
        myShapeBar = new MyShapeBar(0, 32, 2*(B_WIDTH/14), 130);
        myStrokeWidth = new MyStrokeWidth(2*(B_WIDTH/14)-2, 32, B_WIDTH/14, 130);
        myFreeDrawing = new MyFreeDrawing(3*(B_WIDTH/14)-4, 32, B_WIDTH/14, 130);
        myCurves = new MyCurves(4*(B_WIDTH/14)-6, 32, B_WIDTH/14, 130);
        myGrid = MyGrid.getInstance(5*(B_WIDTH/14)-8, 32, B_WIDTH/14, 130);
        myLayerBar = new MyLayerBar(12*(B_WIDTH/14)-12, 32, 2*(B_WIDTH/14)+18, B_HEIGHT-32);
        myMenuBar = new MyMenuBar(0, 0, B_WIDTH, 32);
        myDrawingBoard = new MyDrawingBoard(0, 162, 12*(B_WIDTH/14) +4, B_HEIGHT-162);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        myDrawingBoard.paint(g, this);
        myLayerBar.paint(g, this);
        myColorBar.paint(g, this);
        myGrid.paint(g, this);
        myCurves.paint(g, this);
        myFreeDrawing.paint(g, this);
        myStrokeWidth.paint(g, this);
        myShapeBar.paint(g, this);
        myMenuBar.paint(g, this);

    }


    @Override
	public void mouseClicked(MouseEvent e) {
        if(!windowOpened) {
            myMenuBar.onClick(e.getX(), e.getY());
            myColorBar.onClick(e.getX(), e.getY());
            myMenuBar.onClick(e.getX(), e.getY());
            myShapeBar.onClick(e.getX(), e.getY());
            myLayerBar.onClick(e.getX(), e.getY());
            myStrokeWidth.onClick(e.getX(), e.getY());
            myFreeDrawing.onClick(e.getX(), e.getY());
            myCurves.onClick(e.getX(), e.getY());
            myGrid.onClick(e.getX(), e.getY());
            if(MyLayerBar.getCurrentButton() == null ){
                if (myDrawingBoard.isPressed(e.getX(), e.getY()) || myShapeBar.isPressed(e.getX(), e.getY()) || myFreeDrawing.isPressed(e.getX(), e.getY()) || myCurves.isPressed(e.getX(), e.getY()))  myLayerBar.setDisplayMessage(true);
            }else {
                myLayerBar.setDisplayMessage(false);
                myDrawingBoard.onClick(e.getX(), e.getY());
            }
        }

    }


	// MOUSE LISTENERS
	@Override
	public void mousePressed(MouseEvent e) {
        myMenuBar.onPress(e.getX(), e.getY());
        myColorBar.onPress(e.getX(), e.getY());
        myShapeBar.onPress(e.getX(), e.getY());
        myLayerBar.onPress(e.getX(), e.getY());
        myStrokeWidth.onPress(e.getX(), e.getY());
        myFreeDrawing.onPress(e.getX(), e.getY());
        myCurves.onPress(e.getX(), e.getY());
        myGrid.onPress(e.getX(), e.getY());
        if(MyLayerBar.getCurrentButton() == null ){
            if (myDrawingBoard.isPressed(e.getX(), e.getY()) || myShapeBar.isPressed(e.getX(), e.getY()) || myFreeDrawing.isPressed(e.getX(), e.getY()) || myCurves.isPressed(e.getX(), e.getY()))  myLayerBar.setDisplayMessage(true);
        }else {
            myLayerBar.setDisplayMessage(false);
            myDrawingBoard.onPress(e.getX(), e.getY());
        }

	}

	@Override
	public void mouseReleased(MouseEvent e) {
        myMenuBar.onRelease(e.getX(), e.getY());
        myColorBar.onRelease(e.getX(), e.getY());
        myShapeBar.onRelease(e.getX(), e.getY());
        myLayerBar.onRelease(e.getX(), e.getY());
        myStrokeWidth.onRelease(e.getX(), e.getY());
        myFreeDrawing.onRelease(e.getX(), e.getY());
        myCurves.onRelease(e.getX(), e.getY());
        myGrid.onRelease(e.getX(), e.getY());
        if(MyLayerBar.getCurrentButton() == null ){
            if (myDrawingBoard.isPressed(e.getX(), e.getY()) || myShapeBar.isPressed(e.getX(), e.getY()) || myFreeDrawing.isPressed(e.getX(), e.getY()) || myCurves.isPressed(e.getX(), e.getY()))  myLayerBar.setDisplayMessage(true);
        }else {
            myLayerBar.setDisplayMessage(false);
            myDrawingBoard.onRelease(e.getX(), e.getY());
        }
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
        myColorBar.onDrag(e.getX(), e.getY());
        if(MyLayerBar.getCurrentButton() == null ){
            if (myDrawingBoard.isPressed(e.getX(), e.getY()) || myShapeBar.isPressed(e.getX(), e.getY()) || myFreeDrawing.isPressed(e.getX(), e.getY()) || myCurves.isPressed(e.getX(), e.getY()))  myLayerBar.setDisplayMessage(true);
        }else {
            myLayerBar.setDisplayMessage(false);
            myDrawingBoard.onDrag(e.getX(), e.getY());
        }


	}

	@Override
	public void mouseMoved(MouseEvent e) {
        myMenuBar.onMove(e.getX(), e.getY());
        myColorBar.onMove(e.getX(), e.getY());
        if(!windowOpened) {
            myMenuBar.onMove(e.getX(), e.getY());
            myLayerBar.onMove(e.getX(), e.getY());
            myStrokeWidth.onMove(e.getX(), e.getY());
            myFreeDrawing.onMove(e.getX(), e.getY());
            myCurves.onMove(e.getX(), e.getY());
            myGrid.onMove(e.getX(), e.getY());
            if (!myMenuBar.isFilePressed()) myShapeBar.onMove(e.getX(), e.getY());
        }

    }

	// refreshing
    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().sync();
        repaint();
    }
}