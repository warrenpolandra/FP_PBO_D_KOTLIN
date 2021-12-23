package com.main;


//camera
public class Camera {
	
	private float x, y;
	private int window_width;
	private int window_height;
	
	public Camera(float x, float y, int window_width, int window_height) {
		this.x = x;
		this.y = y;
		this.window_height = window_height;
		this.window_width = window_width;
	}
	
	public void tick(GameObject object) {
		
		//camera position
		x += ((object.getX() - x) - window_width/2) * 0.05f;
		y += ((object.getY() - y) - window_height/2) * 0.05f;
		
		if(Game.state == Game.STATE.MINIGAME) {
			if(Game.curMinGame == Game.CurMinGame.MAZE) {
				//maze border camera
				if(x <= 0) x = 0;
				if(x >= window_width + 90) x = window_width + 90;
				if(y <= 0) y = 0;
				if(y >= window_height + 985) y = window_height + 985;
				
			}
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
