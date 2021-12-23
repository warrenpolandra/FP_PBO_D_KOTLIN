package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Menu {
	
	private BufferedImage playButton;
	private BufferedImage quitButton;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Menu() {
		playButton = loader.loadImage("/playButton.png");
		quitButton = loader.loadImage("/quitButton.png");
	}
	
	//public Rectangle settingsButton = new Rectangle(25, 400, 90, 90);
	
	public void render(Graphics g) {
		
		Font font0 = new Font("tower ruins regular", Font.BOLD, 40);
		g.setFont(font0);
		g.setColor(Color.ORANGE);
		drawString("KOMODO TRAVELLING INDONESIA", Game.window_width, Game.window_height, g, 2, 4);
		drawString("KOTLIN", Game.window_width, Game.window_height, g, 2, 6);

		
		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		//g.drawString("SETTINGS", settingsButton.x + 10, settingsButton.y + 55);
		
		g.drawImage(playButton, Game.window_width / 2 - 100, 280, null);
		g.drawImage(quitButton, Game.window_width - 125, 400, null);
		
	}
	
	public void drawString(String s, int w, int h, Graphics g, int xx, int yy) {
	    FontMetrics fm = g.getFontMetrics();
	    int x = (w - fm.stringWidth(s)) / xx;
	    int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) /yy);
	    g.drawString(s, x, y);
	 }
	
	
}
