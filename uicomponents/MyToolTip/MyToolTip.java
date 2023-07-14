package uicomponents.MyToolTip;

import java.awt.*;
import java.awt.image.BufferedImage;

import static swigncomponents.Board.B_WIDTH;

public class MyToolTip {

    private int x, y, width, height;
    private String text;
    private Font font;

    private MyToolTip(int x, int y, String text){
        this.height = 30;
        font = new Font(Font.SERIF, Font.BOLD, 12);
        this.width = getRectWidthForFontSize(text, font);
        if(x+width > B_WIDTH) this.x = x-width;
        else this.x = x+10;
        this.y = y+10;
        this.text = text;
    }

    public static MyToolTip getInstance(int x, int y, String text){
        return new MyToolTip(x, y, text);

    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width+8, height+4);
        g.setColor(Color.GRAY);
        g.fillRect(x+2, y+2, width+4, height);

        // Adjusting the font of the text
        // Creating the object for the font
        g.setFont(font); // Setting the font

        // Setting the x and y-coordinates for the text
        int x_text = x+4+(width - g.getFontMetrics().stringWidth(text))/2;
        int y_text = y+(height + (g.getFontMetrics().getAscent()-g.getFontMetrics().getDescent()))/2;

        g.setColor(Color.BLACK); // Setting the color of the text
        g.drawString(text, x_text, y_text); // Drawing the text in the canvas

    }

    public int getRectWidthForFontSize(String text, Font font) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        g2d.dispose();
        return width;
    }

}
