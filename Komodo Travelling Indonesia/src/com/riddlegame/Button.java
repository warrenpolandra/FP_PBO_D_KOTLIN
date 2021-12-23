package com.riddlegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.BufferedImageLoader;

public class Button {
	
	private String choice;
	boolean pressed = false;
	int num, x, y;
	private Rectangle rectangle;
	private BufferedImage button;
	private BufferedImageLoader loader = new BufferedImageLoader();
	Font font = new Font("tower ruins regular", Font.BOLD, 35);
	
	public Button(String choice, int num) {
		this.choice = choice;
		this.num = num;
		if(num%2 == 0) {
			this.x = 520;
			this.y = 250 + (num/4)*130;
		}else {
			this.x = 150;
			this.y = 250 + (num/2)*130;
		}
		this.button = loader.loadImage("/wood_button.png");
		this.rectangle = new Rectangle(x, y, 300, 100);
		
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public void render(Graphics g) {
		g.drawImage(button, x-3, y-5, null);
		drawStringMid(g, choice, rectangle, font);
	}
	
	public void drawStringMid(Graphics g, String text, Rectangle rect, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setColor(Color.white);
	    g.setFont(font);
	    g.drawString(text, x, y);
	}
	
}
