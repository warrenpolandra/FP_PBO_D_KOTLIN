package com.memorygame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Card {
	public int width = 80;
	public int height = 90;

	private int x, y, xDraw, yDraw;
	private int position; // 0 - 15
	private int type; // 0 - 7
	private BufferedImage cardBack, cardImg;
	private CardStatus status = CardStatus.CLOSED;
	
	public Card(int position, int type, BufferedImage cardBack, BufferedImage cardImg) {
		this.x = position % 4;
		if(this.x == 0) {
			this.x = 3;
		}else {
			this.x--;
		}
		this.y = (int) Math.ceil((float)position/(float)4);
		this.y--;
		
		this.position = position;
		this.type = type;
		this.cardBack = cardBack;
		this.cardImg = cardImg;
		this.xDraw = 285 + (x * width + x * 30);
		this.yDraw = 45 + (y * height + y * 30);
	}
	
	public void tick() {
		
	}

	
	public void render(Graphics g) {
		if(status == CardStatus.CLOSED) {
			//back of the card
			g.drawImage(cardBack, xDraw, yDraw, null);
		}else if(status == CardStatus.OPENED) {
			g.drawImage(cardImg, xDraw, yDraw, null);
		}else if(status == CardStatus.SOLVED) {
			//draw nothing
		}
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

	public int getPosition() {
		return position;
	}
	
	public int getxDraw() {
		return xDraw;
	}

	public void setxDraw(int xDraw) {
		this.xDraw = xDraw;
	}

	public int getyDraw() {
		return yDraw;
	}

	public void setyDraw(int yDraw) {
		this.yDraw = yDraw;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void setStatus(CardStatus status) {
		this.status = status;
	}
	
	public CardStatus getStatus() {
		return status;
	}
	
}
