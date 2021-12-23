package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game.STATE;
import com.main.Handler.Facing;

public class Player extends GameObject{

	Handler handler;
	Game game;
	
	private BufferedImage rider_right = null;
	private BufferedImage rider_left = null;
	private BufferedImage rider_up = null;
	private BufferedImage rider_down = null;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		
		rider_right = loader.loadImage("/rider_right.png");
		rider_left = loader.loadImage("/rider_left.png");
		rider_up = loader.loadImage("/rider_up.png");
		rider_down = loader.loadImage("/rider_down.png");
	}

	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		//movement
		if(Game.state == STATE.MINIGAME && Game.curMinGame == Game.CurMinGame.MAZE) {
			if(handler.isUp()) velY = -5;
			else if(!handler.isDown()) velY = 0;
			
			if(handler.isDown()) velY = 5;
			else if(!handler.isUp()) velY = 0;
			
			if(handler.isRight()) velX = 5;
			else if(!handler.isLeft()) velX = 0;
			
			if(handler.isLeft()) velX = -5;
			else if(!handler.isRight()) velX = 0;
			
			if(x > 2130 || x < -50 || y > 2130 || y < -50) {
				if(handler.papuaFinished == false) {
					game.stoneTabletCounter++;
					handler.papuaFinished = true;
				}
				Game.state = Game.STATE.WIN;
			}
		}
	}
	
	//collision with other objects
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BUSH) {
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
		}
	}
	
	//render graphic
	public void render(Graphics g) {
		//if(velX == 0 && velY == 0) 
		if(Handler.face == Facing.RIGHT) g.drawImage(rider_right, x, y, null);
		else if(Handler.face == Facing.LEFT) g.drawImage(rider_left, x, y, null);
		else if(Handler.face == Facing.UP) g.drawImage(rider_up, x + 9, y + 12, null);
		else if(Handler.face == Facing.DOWN) g.drawImage(rider_down, x + 9, y + 12, null);
	}
	
	//collision box
	public Rectangle getBounds() {
		if(Handler.face == Facing.LEFT || Handler.face == Facing.RIGHT) {
			return new Rectangle(x + 12, y + 36, 32, 22);
		}else {
			return new Rectangle(x + 12, y + 36, 32, 22);
		}
	}
}
