package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Start {
	
	public void render(Graphics g) {
		
		Font font0 = new Font("tower ruins regular", Font.BOLD, 40);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		drawString("Click anywhere to start!", Game.window_width, Game.window_height, g, 2, 2);

	}
	
	public void drawString(String s, int w, int h, Graphics g, int xx, int yy) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / xx;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) /yy);
	    g.drawString(s, x, y);
	 }
}
