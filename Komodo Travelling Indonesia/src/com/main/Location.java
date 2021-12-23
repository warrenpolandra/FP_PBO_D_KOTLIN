package com.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Location {
	
	private String name, description, funFact;
	private int x, y;
	private boolean unlocked;
	BufferedImageLoader loader = new BufferedImageLoader();
	BufferedImage pin, lock;
	
	public Location(String name, String description, String funFact, int x, int y, boolean unlocked) {
		
		this.name = name;
		this.description = description;
		this.funFact = funFact;
		this.x = x;
		this.y = y;
		this.unlocked = unlocked;
		this.pin = loader.loadImage("/pin.png");
		this.lock = loader.loadImage("/lock.png");
	}
	
	public void tick() {
		
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean unlocked) {
		this.unlocked = unlocked;
	}

	public void render(Graphics g) {
		g.drawImage(pin, x, y, null);
		if(!this.isUnlocked()) {
			g.drawImage(lock, x, y, null);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFunFact() {
		return funFact;
	}

	public void setFunFact(String funFact) {
		this.funFact = funFact;
	}
	
}
