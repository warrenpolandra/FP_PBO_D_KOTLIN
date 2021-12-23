package com.quiz;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.BufferedImageLoader;

public class Quiz {
	private String question, fact;
	Font font1 = new Font("tower ruins regular", Font.BOLD, 32);
	Font font2 = new Font("tower ruins regular", Font.BOLD, 30);
	Font font3 = new Font("tower ruins regular", Font.BOLD, 20);
	Rectangle rectangle1 = new Rectangle(40, 20, 900, 10);
	Rectangle rectangle2 = new Rectangle(40, 20, 900, 190);
	Rectangle rectangle3 = new Rectangle(95, 400, 800, 90);
	
	private BufferedImage true_button, false_button, true_pressed, false_pressed;
	private BufferedImage cross, check, cloud_bg, bush_bg;
	private BufferedImageLoader loader = new BufferedImageLoader();
	boolean key, answer, solved = false, answered = false;
	private boolean falsePressed = false;
	private boolean truePressed = false;

	public enum CHECK{
		TRUE,
		FALSE,
		NULL
	};
	
	private CHECK tof = CHECK.NULL;
	
	public Quiz(String question, String fact, boolean tof) {
		this.question = question;
		this.fact = fact;
		this.key = tof;
		
		this.cloud_bg = loader.loadImage("/cloud_bg.png");
		this.bush_bg = loader.loadImage("/bush_bg.png");
		
		this.true_button = loader.loadImage("/true.png");
		this.true_pressed = loader.loadImage("/true_pressed.png");
		this.false_button = loader.loadImage("/false.png");
		this.false_pressed = loader.loadImage("/false_pressed.png");
		
		this.check = loader.loadImage("/check.png");
		this.cross = loader.loadImage("/cross.png");
	}
	
	public void render(Graphics g){
		g.drawImage(cloud_bg, 40, 20, null);
		g.setColor(Color.BLACK);
		drawStringMid(g, "Tebak-tebakan seputar Pulau Kalimantan", rectangle1, font1);
		drawStringMid(g, getQuestion(), rectangle2, font2);
		
		if(truePressed == true) {
			g.drawImage(true_pressed, 280, 245, null);
		}else {
			g.drawImage(true_button, 280, 245, null);
		}
		
		if(falsePressed == true) {
			g.drawImage(false_pressed, 580, 245, null);
		}else {
			g.drawImage(false_button, 580, 245, null);
		}
		
		g.drawImage(bush_bg, 95, 400, null);
		
		if(tof == CHECK.TRUE) {
			g.drawImage(check, 870, 20, null);
			g.setColor(Color.WHITE);
			drawStringMid(g, getFact(), rectangle3, font3);
		}else if(tof == CHECK.FALSE) {
			g.drawImage(cross, 870, 20, null);
		}
		
	}

	public void drawStringMid(Graphics g, String text, Rectangle rect, Font font) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
		    if (text.charAt(i) == '\n') {
		        count++;
		    }
		}
	    FontMetrics metrics = g.getFontMetrics(font);
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent() - 20*count;
	    g.setFont(font);
	    for (String line : text.split("\n")) {
	    	int x = rect.x + (rect.width - metrics.stringWidth(line)) / 2;
	        g.drawString(line, x, y += g.getFontMetrics().getHeight());
	    }
	}
	
	public boolean isFalsePressed() {
		return falsePressed;
	}

	public void setFalsePressed(boolean falsePressed) {
		this.falsePressed = falsePressed;
	}

	public boolean isTruePressed() {
		return truePressed;
	}

	public void setTruePressed(boolean truePressed) {
		this.truePressed = truePressed;
	}
	
	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
	
	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}
	
	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public boolean getKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}

	public CHECK getStatus() {
		return tof;
	}

	public void setStatus(CHECK tof) {
		this.tof = tof;
	}

	public boolean getAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
}
