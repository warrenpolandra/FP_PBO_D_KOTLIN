package com.rockpaperscissors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.BufferedImageLoader;
import com.memorygame.CardStatus;

public class RPSCard {
	public int width = 120;
	public int height = 135;
	private int x, y; //position
	private int type; //0 - 2
	private CardStatus status;
	private boolean playerCard;
	
	//rock paper scissors card image
	private BufferedImageLoader loader = new BufferedImageLoader(); 
	private BufferedImage cardBack = null;
	private BufferedImage cardImg = null;
	
	public RPSCard(int x, int y, int type, CardStatus status, boolean playerCard) {
		this.type = type;
		if(this.type == 0) {
			this.cardImg = loader.loadImage("/rock.png");
		}else if(this.type == 1) {
			this.cardImg = loader.loadImage("/paper.png");
		}else if(this.type == 2) {
			this.cardImg = loader.loadImage("/scissors.png");
		}
		//0 : Rock
		//1 : Paper
		//2 : Scissors
		
		cardBack = loader.loadImage("/rps_card_back.png");
		
		this.x = x;
		this.y = y;
		this.status = status;
		this.playerCard = playerCard;
		
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(status == CardStatus.CLOSED) {
			g.drawImage(cardBack, x, y, null);
		}else {
			g.drawImage(cardImg, x, y, null);
		}
	}
	
	public CardStatus getStatus() {
		return status;
	}

	public void setStatus(CardStatus status) {
		this.status = status;
	}

	public boolean isPlayerCard() {
		return playerCard;
	}

	public void setPlayerCard(boolean playerCard) {
		this.playerCard = playerCard;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
		if(this.type == 0) {
			this.cardImg = loader.loadImage("/rock.png");
		}else if(this.type == 1) {
			this.cardImg = loader.loadImage("/paper.png");
		}else if(this.type == 2) {
			this.cardImg = loader.loadImage("/scissors.png");
		}
	}
}
