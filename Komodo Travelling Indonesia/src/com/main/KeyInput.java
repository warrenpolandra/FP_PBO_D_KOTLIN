package com.main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.main.Handler.Facing;


//key input
public class KeyInput extends KeyAdapter{
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) {
					handler.setUp(true);
					Handler.face = Facing.UP;
				}
				if(key == KeyEvent.VK_S) {
					handler.setDown(true);
					Handler.face = Facing.DOWN;
				}
				if(key == KeyEvent.VK_A) {
					handler.setLeft(true);
					Handler.face = Facing.LEFT;
				}
				if(key == KeyEvent.VK_D) {
					handler.setRight(true);
					Handler.face = Facing.RIGHT;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
			}
		}
	}
}
