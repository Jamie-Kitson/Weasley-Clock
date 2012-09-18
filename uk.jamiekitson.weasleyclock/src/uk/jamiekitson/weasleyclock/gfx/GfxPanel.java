package uk.jamiekitson.weasleyclock.gfx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class GfxPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private Graphics2D gfx;
    private Image bufferImage;
    
    public void makeGraphics()
    {
    	Dimension size = this.getSize();
    	bufferImage = this.createImage(size.width, size.height);
    	gfx = (Graphics2D)bufferImage.getGraphics();
    	gfx.setBackground(Color.white);
    	gfx.clearRect(0, 0, size.width, size.height);
    	
    	setPreferredSize(new Dimension(size.width, size.height));
    }
    
    public void drawText(String txt, int x, int y)
    {
    	gfx.drawString(txt, x, y);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(bufferImage, 0, 0, null);
    }

	public void erase()
	{
        Dimension size = this.getSize();
		gfx.clearRect(0, 0, size.width, size.height);
	}

}
