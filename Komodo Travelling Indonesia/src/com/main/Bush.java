package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// wall/border/block
public class Bush extends GameObject{

	BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage bush = null;
	
	
	public Bush(int x, int y, ID id) {
		super(x, y, id);
		this.bush = loader.loadImage("/bush.png");
	}

	
	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(bush, x, y, 32, 32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
